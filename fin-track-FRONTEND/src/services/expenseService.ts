import api from "./api";
import { Expense } from "../types/interfaces";

const BASE_URL = "/expense";

export const createExpense = async (expenseData: Expense): Promise<Expense> => {
  try {
    const response = await api.post(BASE_URL, expenseData);
    return response.data;
  } catch (error) {
    throw new Error("Error creating expense: " + (error as Error).message);
  }
};

export const getExpenses = async (): Promise<Expense[]> => {
  try {
    const response = await api.get(BASE_URL);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching expenses: " + (error as Error).message);
  }
};

export const getExpenseById = async (id: string): Promise<Expense> => {
  try {
    const response = await api.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching expense: " + (error as Error).message);
  }
};

export const updateExpense = async (id: string, expenseData: Expense): Promise<Expense> => {
  try {
    const response = await api.put(`${BASE_URL}/${id}`, expenseData);
    return response.data;
  } catch (error) {
    throw new Error("Error updating expense: " + (error as Error).message);
  }
};

export const deleteExpense = async (id: string): Promise<void> => {
  try {
    await api.delete(`${BASE_URL}/${id}`);
  } catch (error) {
    throw new Error("Error deleting expense: " + (error as Error).message);
  }
};
