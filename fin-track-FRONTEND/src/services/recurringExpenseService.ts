import { RecurringExpense } from "../types/interfaces";
import api from "./api";

const API_URL = "/recurring-expense";

export const createRecurringExpense = async (recurringExpenseData: RecurringExpense): Promise<RecurringExpense> => {
  try {
    const response = await api.post(API_URL, recurringExpenseData);
    return response.data;
  } catch (error) {
    throw new Error("Error creating recurring expense: " + (error as Error).message);
  }
}

export const getRecurringExpenses = async (): Promise<RecurringExpense[]> => {  
  try {
    const response = await api.get(API_URL);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching recurring expenses: " + (error as Error).message);
  }
}

export const getRecurringExpenseById = async (id: string): Promise<RecurringExpense> => {
  try {
    const response = await api.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching recurring expense: " + (error as Error).message);
  }
}

export const updateRecurringExpense = async (id: string, recurringExpenseData: RecurringExpense): Promise<RecurringExpense> => {
  try {
    const response = await api.put(`${API_URL}/${id}`, recurringExpenseData);
    return response.data;
  } catch (error) {
    throw new Error("Error updating recurring expense: " + (error as Error).message);
  }
}

export const deleteRecurringExpense = async (id: string): Promise<void> => {
  try {
    await api.delete(`${API_URL}/${id}`);
  } catch (error) {
    throw new Error("Error deleting recurring expense: " + (error as Error).message);
  }
}