export interface Family {
  id: number;
  name: string;
}

export interface User {
  id: number;
  email: string;
  password: string;
  name: string;
  family: Family;
  role: "ROLE_USER" | "ROLE_ADMIN";
}

export interface Expense {
  id?: number;
  user: User;
  amount: number;
  category: string;
  description: string;
  date: string | null;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  name: string;
  familyId: number;
}

export interface JwtResponse {
  token: string;
  user: User;
}

export interface RecurringExpense {
  id?: number;
  user: User;
  amount: number;
  category: string;
  description: string;
  frequency: 'WEEKLY' | 'MONTHLY' | 'YEARLY';
}