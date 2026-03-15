import { DayEntry } from '../types';
import { subDays, format, isSameDay, parseISO, startOfDay } from 'date-fns';

const STORAGE_KEY = 'stepwise_data';
const SETTINGS_KEY = 'stepwise_settings';

export const getStoredEntries = (): DayEntry[] => {
  const data = localStorage.getItem(STORAGE_KEY);
  if (data) return JSON.parse(data);
  
  // Generate dummy data if empty
  const dummyEntries: DayEntry[] = [];
  const today = new Date();
  for (let i = 14; i >= 0; i--) {
    const date = format(subDays(today, i), 'yyyy-MM-dd');
    const baseGoal = 8000;
    
    // Ensure at least a 2-day streak (today and yesterday hit goal)
    let steps;
    if (i === 0 || i === 1) {
      steps = baseGoal + Math.round(Math.random() * 2000);
    } else {
      steps = Math.round(6000 + Math.random() * 4000);
    }

    dummyEntries.push({
      date,
      steps,
      goal: baseGoal
    });
  }
  saveEntries(dummyEntries);
  return dummyEntries;
};

export const saveEntries = (entries: DayEntry[]) => {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(entries));
};

export const getStoredSettings = () => {
  const data = localStorage.getItem(SETTINGS_KEY);
  return data ? JSON.parse(data) : { baseGoal: 8000 };
};

export const saveSettings = (settings: { baseGoal: number }) => {
  localStorage.setItem(SETTINGS_KEY, JSON.stringify(settings));
};

export const calculateGoal = (entries: DayEntry[], baseGoal: number): { goal: number; trend: 'up' | 'down' | 'stable'; motivation: string } => {
  if (entries.length < 3) return { goal: baseGoal, trend: 'stable', motivation: "Starting fresh! Let's hit your base goal." };

  // Get last 7 days (excluding today if it's already there, or just the most recent 7)
  const last7 = entries.slice(-7);
  const avg = last7.reduce((sum, e) => sum + e.steps, 0) / last7.length;

  // Trend analysis: compare last 3 days to the 4 days before that
  const recent3 = last7.slice(-3);
  const prev4 = last7.slice(0, -3);
  
  const recentAvg = recent3.reduce((sum, e) => sum + e.steps, 0) / recent3.length;
  const prevAvg = prev4.length > 0 ? prev4.reduce((sum, e) => sum + e.steps, 0) / prev4.length : avg;

  let adjustment = 1;
  let trend: 'up' | 'down' | 'stable' = 'stable';
  let motivation = "You're consistent! Keeping the goal steady to maintain your rhythm.";

  if (recentAvg > prevAvg * 1.1) {
    adjustment = 1.05;
    trend = 'up';
    motivation = "You're on fire! Nudging your goal up to keep the momentum going.";
  } else if (recentAvg < prevAvg * 0.9) {
    adjustment = 0.95;
    trend = 'down';
    motivation = "Taking it easy? We've lowered your goal slightly to help you get back on track.";
  }

  return { 
    goal: Math.round(avg * adjustment) || baseGoal, 
    trend, 
    motivation 
  };
};

export const getStreak = (entries: DayEntry[]): number => {
  let streak = 0;
  const sorted = [...entries].sort((a, b) => b.date.localeCompare(a.date));
  
  // Check if today or yesterday was hit
  const today = format(new Date(), 'yyyy-MM-dd');
  const yesterday = format(subDays(new Date(), 1), 'yyyy-MM-dd');
  
  const lastEntry = sorted[0];
  if (!lastEntry) return 0;
  
  if (lastEntry.date !== today && lastEntry.date !== yesterday) return 0;

  for (const entry of sorted) {
    if (entry.steps >= entry.goal) {
      streak++;
    } else {
      break;
    }
  }
  return streak;
};

export const getPersonalBest = (entries: DayEntry[]): number => {
  if (entries.length === 0) return 0;
  return Math.max(...entries.map(e => e.steps));
};
