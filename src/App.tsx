import React, { useState, useMemo } from 'react';
import { 
  Home, 
  BarChart2, 
  History as HistoryIcon, 
  Music, 
  Settings as SettingsIcon,
  TrendingUp,
  Trophy,
  Edit2,
  Check,
  X,
  Play,
  RotateCcw,
  ChevronRight,
  ExternalLink
} from 'lucide-react';
import { 
  BarChart, 
  Bar, 
  XAxis, 
  YAxis, 
  CartesianGrid, 
  Tooltip, 
  ResponsiveContainer, 
  Cell,
  LineChart,
  Line
} from 'recharts';
import { format, parseISO, subDays } from 'date-fns';
import { clsx, type ClassValue } from 'clsx';
import { twMerge } from 'tailwind-merge';

function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

type Tab = 'home' | 'week' | 'history' | 'music' | 'settings';

// --- Dummy Data ---
const TODAY_STEPS = 6843;
const DAILY_GOAL = 8500;
const STREAK = 5;
const PACE = 92;
const WEEK_DATA = [
  { day: 'Mon', steps: 7200, goal: 8500 },
  { day: 'Tue', steps: 9100, goal: 8500 },
  { day: 'Wed', steps: 5400, goal: 8500 },
  { day: 'Thu', steps: 8800, goal: 8500 },
  { day: 'Fri', steps: 6843, goal: 8500 },
  { day: 'Sat', steps: 0, goal: 8500 },
  { day: 'Sun', steps: 0, goal: 8500 },
];

const HISTORY_DATA = [
  { date: 'Today', steps: 6843, goal: 8500, hit: false },
  { date: 'Yesterday', steps: 8800, goal: 8500, hit: true },
  { date: 'Mar 13', steps: 5400, goal: 8500, hit: false },
  { date: 'Mar 12', steps: 9100, goal: 8500, hit: true },
  { date: 'Mar 11', steps: 7200, goal: 7000, hit: true },
  { date: 'Mar 10', steps: 8200, goal: 7000, hit: true },
  { date: 'Mar 09', steps: 7500, goal: 7000, hit: true },
  { date: 'Mar 08', steps: 9000, goal: 7000, hit: true },
  { date: 'Mar 07', steps: 6800, goal: 7000, hit: false },
  { date: 'Mar 06', steps: 7100, goal: 7000, hit: true },
];

const PLAYLIST = [
  { title: "Blinding Lights", artist: "The Weeknd", duration: "3:20", color: "bg-rose-100" },
  { title: "Levitating", artist: "Dua Lipa", duration: "3:23", color: "bg-emerald-100" },
  { title: "As It Was", artist: "Harry Styles", duration: "2:37", color: "bg-blue-100" },
  { title: "Stay", artist: "The Kid LAROI", duration: "2:21", color: "bg-yellow-100" },
  { title: "Shivers", artist: "Ed Sheeran", duration: "3:27", color: "bg-purple-100" },
];

// --- Components ---

const Card = ({ children, className }: { children: React.ReactNode; className?: string }) => (
  <div className={cn("bg-white rounded-[24px] p-6 card-shadow border border-white/50", className)}>
    {children}
  </div>
);

const PillButton = ({ children, className, onClick, variant = 'primary' }: { children: React.ReactNode; className?: string; onClick?: () => void; variant?: 'primary' | 'lavender' | 'spotify' }) => (
  <button 
    onClick={onClick}
    className={cn(
      "px-6 py-4 rounded-full font-bold transition-all active:scale-95 flex items-center justify-center gap-2",
      variant === 'primary' && "bg-primary-green text-white",
      variant === 'lavender' && "bg-lavender text-white",
      variant === 'spotify' && "bg-spotify-green text-white",
      className
    )}
  >
    {children}
  </button>
);

const NavItem = ({ active, icon: Icon, label, onClick }: { active: boolean; icon: any; label: string; onClick: () => void }) => (
  <button 
    onClick={onClick}
    className={cn(
      "flex flex-col items-center gap-1 flex-1 py-2 transition-colors",
      active ? "text-primary-green" : "text-text-secondary"
    )}
  >
    <Icon size={24} strokeWidth={active ? 2.5 : 2} />
    <span className="text-[10px] font-bold uppercase tracking-wider">{label}</span>
  </button>
);

// --- Screens ---

const HomeScreen = () => {
  const progress = (TODAY_STEPS / DAILY_GOAL) * 100;
  const circumference = 2 * Math.PI * 90;
  const strokeDashoffset = circumference - (progress / 100) * circumference;

  return (
    <div className="space-y-8 animate-in fade-in slide-in-from-bottom-4">
      <div className="flex items-center justify-between">
        <p className="text-text-secondary font-medium">Good evening, let's finish strong 🌿</p>
      </div>

      <div className="flex flex-col items-center justify-center py-8">
        <div className="relative w-64 h-64 flex items-center justify-center">
          <svg className="w-full h-full transform -rotate-90">
            <circle
              cx="128"
              cy="128"
              r="90"
              stroke="currentColor"
              strokeWidth="24"
              fill="transparent"
              className="text-lavender/20"
            />
            <circle
              cx="128"
              cy="128"
              r="90"
              stroke="currentColor"
              strokeWidth="24"
              fill="transparent"
              strokeDasharray={circumference}
              strokeDashoffset={strokeDashoffset}
              strokeLinecap="round"
              className="text-primary-green transition-all duration-1000 ease-out"
            />
          </svg>
          <div className="absolute inset-0 flex flex-col items-center justify-center text-center">
            <span className="text-5xl font-extrabold text-text-primary tabular-nums">{TODAY_STEPS.toLocaleString()}</span>
            <span className="text-xs font-bold text-text-secondary uppercase tracking-widest mt-1">steps today</span>
          </div>
        </div>
      </div>

      <Card className="grid grid-cols-3 gap-4">
        <div className="text-center">
          <p className="text-[10px] font-bold text-text-secondary uppercase tracking-wider mb-1">Pace</p>
          <p className="text-xl font-extrabold text-text-primary">{PACE}</p>
          <p className="text-[10px] text-text-secondary">steps/min</p>
        </div>
        <div className="text-center">
          <p className="text-[10px] font-bold text-text-secondary uppercase tracking-wider mb-1">Streak</p>
          <div className="flex items-center justify-center gap-1">
            <p className="text-xl font-extrabold text-text-primary">{STREAK}</p>
            <span className="bg-lemonade-yellow px-1.5 py-0.5 rounded-md text-[10px] font-bold">days 🔥</span>
          </div>
        </div>
        <div className="text-center">
          <p className="text-[10px] font-bold text-text-secondary uppercase tracking-wider mb-1">Day</p>
          <p className="text-xl font-extrabold text-text-primary">Push</p>
          <p className="text-[10px] text-text-secondary">Day</p>
        </div>
      </Card>

      <PillButton variant="lavender" className="w-full">
        Log Steps Manually
      </PillButton>

      <p className="text-center text-text-secondary font-medium">
        You're 1,657 steps away — about a 18 min walk
      </p>
    </div>
  );
};

const WeekScreen = () => {
  return (
    <div className="space-y-8 animate-in fade-in slide-in-from-bottom-4">
      <h2 className="text-2xl font-bold">Weekly View</h2>

      <Card className="p-4">
        <div className="h-64 w-full">
          <ResponsiveContainer width="100%" height="100%">
            <BarChart data={WEEK_DATA}>
              <CartesianGrid strokeDasharray="3 3" vertical={false} stroke="#F1F5F9" />
              <XAxis 
                dataKey="day" 
                axisLine={false} 
                tickLine={false} 
                tick={{ fontSize: 12, fontWeight: 600, fill: '#6E6E73' }} 
              />
              <YAxis hide />
              <Bar dataKey="steps" radius={[12, 12, 0, 0]} barSize={32}>
                {WEEK_DATA.map((entry, index) => (
                  <Cell 
                    key={`cell-${index}`} 
                    fill={entry.day === 'Fri' ? '#F9E45B' : (entry.steps >= entry.goal ? '#4CAF7D' : '#B39DDB')} 
                  />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </div>
      </Card>

      <p className="text-lg font-medium text-text-primary">
        Your average this week is 7,469 steps
      </p>

      <Card className="flex items-center gap-4 py-4">
        <div className="bg-lemonade-yellow/20 p-2 rounded-xl">
          <Trophy className="text-lemonade-yellow" size={24} />
        </div>
        <div>
          <p className="text-sm font-bold text-text-primary">Personal Best 🏅</p>
          <p className="text-sm text-text-secondary">Tuesday · 9,100 steps</p>
        </div>
      </Card>

      <div className="flex items-center gap-2 text-primary-green font-bold">
        <TrendingUp size={20} />
        <span>↑ Trending up this week</span>
      </div>
    </div>
  );
};

const HistoryScreen = () => {
  return (
    <div className="space-y-8 animate-in fade-in slide-in-from-bottom-4">
      <h2 className="text-2xl font-bold">8 out of 10 goals hit this month 💪</h2>

      <div className="space-y-6">
        <div>
          <p className="text-[10px] font-bold text-text-secondary uppercase tracking-[0.2em] mb-4">This Week</p>
          <div className="space-y-3">
            {HISTORY_DATA.slice(0, 5).map((item, i) => (
              <div key={i}>
                <HistoryRow item={item} />
              </div>
            ))}
          </div>
        </div>

        <div>
          <p className="text-[10px] font-bold text-text-secondary uppercase tracking-[0.2em] mb-4">Last Week</p>
          <div className="space-y-3">
            {HISTORY_DATA.slice(5).map((item, i) => (
              <div key={i}>
                <HistoryRow item={item} />
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

const HistoryRow = ({ item }: { item: any }) => (
  <Card className="flex items-center justify-between py-4">
    <div className="flex flex-col">
      <span className="font-bold text-text-primary">{item.date}</span>
      <span className="text-[10px] font-bold text-text-secondary uppercase tracking-wider">Goal: {item.goal.toLocaleString()}</span>
    </div>
    <div className="flex flex-col items-center">
      <span className="text-xl font-extrabold text-text-primary tabular-nums">{item.steps.toLocaleString()}</span>
    </div>
    <div className={cn(
      "px-3 py-1 rounded-full font-bold text-xs",
      item.hit ? "bg-primary-green/10 text-primary-green" : "bg-text-secondary/10 text-text-secondary"
    )}>
      {item.hit ? "✓" : "✕"}
    </div>
  </Card>
);

const MusicScreen = () => {
  return (
    <div className="space-y-8 animate-in fade-in slide-in-from-bottom-4">
      <Card className="flex items-center gap-4 py-8">
        <div className="bg-lavender/10 p-3 rounded-2xl">
          <Music className="text-lavender" size={28} />
        </div>
        <p className="text-lg font-bold text-text-primary leading-tight">
          1,657 steps to go · ~18 min walk 🎵
        </p>
      </Card>

      <PillButton className="w-full">
        Generate Playlist
      </PillButton>

      <div className="bg-lavender/5 rounded-[32px] p-6 border border-lavender/10">
        <div className="flex items-center justify-between mb-6">
          <h3 className="font-bold text-text-primary">Generated Playlist</h3>
          <div className="flex items-center gap-1">
            {[12, 18, 14, 20, 16].map((h, i) => (
              <div key={i} className="w-1 bg-primary-green rounded-full" style={{ height: h }} />
            ))}
          </div>
        </div>

        <div className="space-y-4">
          {PLAYLIST.map((song, i) => (
            <div key={i} className="flex items-center gap-4">
              <div className={cn("w-12 h-12 rounded-xl", song.color)} />
              <div className="flex-1">
                <p className="font-bold text-text-primary text-sm leading-tight">{song.title}</p>
                <p className="text-xs text-text-secondary">{song.artist}</p>
              </div>
              <span className="text-xs font-bold text-text-secondary">{song.duration}</span>
            </div>
          ))}
        </div>
      </div>

      <PillButton variant="spotify" className="w-full">
        <Play size={20} fill="currentColor" />
        Open in Spotify
      </PillButton>

      <p className="text-center text-[10px] font-bold text-text-secondary uppercase tracking-widest">
        Based on your most played tracks this week
      </p>
    </div>
  );
};

const SettingsScreen = () => {
  return (
    <div className="space-y-8 animate-in fade-in slide-in-from-bottom-4">
      <h2 className="text-2xl font-bold">Settings</h2>

      <div className="space-y-8 pb-8">
        <SettingsSection title="Goal Settings">
          <SettingsRow label="Base Step Goal" value="8,000" icon={<Edit2 size={16} />} />
          <SettingsToggleRow 
            label="Goal Adjustment" 
            description="Auto-adjusts based on your weekly average" 
            checked={true} 
          />
        </SettingsSection>

        <SettingsSection title="Spotify">
          <div className="flex items-center gap-4 py-3">
            <div className="w-12 h-12 bg-spotify-green rounded-full flex items-center justify-center text-white">
              <Play size={24} fill="currentColor" />
            </div>
            <div className="flex-1">
              <p className="font-bold text-text-primary">Connected as deekshitha</p>
              <div className="flex items-center gap-1.5">
                <div className="w-2 h-2 bg-primary-green rounded-full" />
                <span className="text-xs text-text-secondary">Connected</span>
              </div>
            </div>
            <button className="text-xs font-bold text-rose-500">Disconnect</button>
          </div>
        </SettingsSection>

        <SettingsSection title="Data">
          <button className="w-full text-left py-3 font-bold text-rose-500">Reset All Data</button>
          <button className="w-full text-left py-3 font-bold text-text-primary">Export History</button>
        </SettingsSection>

        <div className="flex flex-col items-center justify-center gap-2 pt-4">
          <p className="text-[10px] font-bold text-text-secondary uppercase tracking-[0.4em]">Grove v1.0</p>
          <span className="text-xl">🌿</span>
        </div>
      </div>
    </div>
  );
};

const SettingsSection = ({ title, children }: { title: string; children: React.ReactNode }) => (
  <div>
    <p className="text-[10px] font-bold text-text-secondary uppercase tracking-[0.2em] mb-3 ml-2">{title}</p>
    <Card className="p-4 divide-y divide-slate-100">
      {children}
    </Card>
  </div>
);

const SettingsRow = ({ label, value, icon }: { label: string; value: string; icon: React.ReactNode }) => (
  <div className="flex items-center justify-between py-3">
    <span className="font-medium text-text-primary">{label}</span>
    <div className="flex items-center gap-2">
      <span className="font-bold text-text-primary">{value}</span>
      <div className="text-text-secondary">{icon}</div>
    </div>
  </div>
);

const SettingsToggleRow = ({ label, description, checked }: { label: string; description: string; checked: boolean }) => (
  <div className="flex items-center justify-between py-3">
    <div className="flex flex-col">
      <span className="font-medium text-text-primary">{label}</span>
      <span className="text-[10px] text-text-secondary">{description}</span>
    </div>
    <div className={cn(
      "w-12 h-6 rounded-full p-1 transition-colors",
      checked ? "bg-primary-green" : "bg-slate-200"
    )}>
      <div className={cn(
        "w-4 h-4 bg-white rounded-full transition-transform",
        checked ? "translate-x-6" : "translate-x-0"
      )} />
    </div>
  </div>
);

// --- Main App ---

export default function App() {
  const [activeTab, setActiveTab] = useState<Tab>('home');

  return (
    <div className="min-h-screen bg-background flex flex-col max-w-md mx-auto relative shadow-2xl">
      <main className="flex-1 overflow-y-auto p-6 pb-24">
        {activeTab === 'home' && <HomeScreen />}
        {activeTab === 'week' && <WeekScreen />}
        {activeTab === 'history' && <HistoryScreen />}
        {activeTab === 'music' && <MusicScreen />}
        {activeTab === 'settings' && <SettingsScreen />}
      </main>

      <nav className="fixed bottom-0 left-0 right-0 max-w-md mx-auto bg-white/80 backdrop-blur-xl border-t border-slate-100 flex px-2 py-1 z-50">
        <NavItem active={activeTab === 'home'} onClick={() => setActiveTab('home')} icon={Home} label="Home" />
        <NavItem active={activeTab === 'week'} onClick={() => setActiveTab('week')} icon={BarChart2} label="Week" />
        <NavItem active={activeTab === 'history'} onClick={() => setActiveTab('history')} icon={HistoryIcon} label="History" />
        <NavItem active={activeTab === 'music'} onClick={() => setActiveTab('music')} icon={Music} label="Music" />
        <NavItem active={activeTab === 'settings'} onClick={() => setActiveTab('settings')} icon={SettingsIcon} label="Settings" />
      </nav>
    </div>
  );
}
