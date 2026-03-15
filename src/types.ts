export interface DayEntry {
  date: string; // ISO string YYYY-MM-DD
  steps: number;
  goal: number;
}

export interface AppSettings {
  baseGoal: number;
}

export type DayType = 'Recovery Day' | 'Steady' | 'Push Day';

export interface StepStats {
  todaySteps: number;
  todayGoal: number;
  streak: number;
  personalBest: number;
  weeklyAverage: number;
  dayType: DayType;
  trend: 'up' | 'down' | 'stable';
  motivation: string;
}
