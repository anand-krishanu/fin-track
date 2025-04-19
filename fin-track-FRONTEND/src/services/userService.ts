import { User } from "../types/interfaces";
import api from "./api";

const BASE_URL = "/user";

export const getUserById = async (id: string): Promise<User> => {
  try {
    const response = await api.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching user: " + (error as Error).message);
  }
}

export const getUsers = async (): Promise<User[]> => {
  try {
    const response = await api.get(BASE_URL);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching users: " + (error as Error).message);
  }
}

export const updateUser = async (id: string, userData: User): Promise<User> => {
  try {
    const response = await api.put(`${BASE_URL}/${id}`, userData);
    return response.data;
  } catch (error) {
    throw new Error("Error updating user: " + (error as Error).message);
  }
}

export const deleteUser = async (id: string): Promise<void> => {
  try {
    await api.delete(`${BASE_URL}/${id}`);
  } catch (error) {
    throw new Error("Error deleting user: " + (error as Error).message);
  }
}