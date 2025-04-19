import { Family } from "../types/interfaces";
import api from "./api";

const BASE_URL = "/family";

export const createFamily = async (familyData: Family): Promise<Family> => {
  try {
    const response = await api.post(BASE_URL, familyData);
    return response.data;
  } catch (error) {
    throw new Error("Error creating family: " + (error as Error).message);
  }
};

export const getFamilies = async (): Promise<Family[]> => {
  try {
    const response = await api.get(BASE_URL);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching families: " + (error as Error).message);
  }
}

export const getFamilyById = async (id: string): Promise<Family> => {
  try {
    const response = await api.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw new Error("Error fetching family: " + (error as Error).message);
  }
}

export const updateFamily = async (id: string, familyData: Family): Promise<Family> => {    
  try {
    const response = await api.put(`${BASE_URL}/${id}`, familyData);
    return response.data;
  } catch (error) {
    throw new Error("Error updating family: " + (error as Error).message);
  }
}

export const deleteFamily = async (id: string): Promise<void> => {
  try {
    await api.delete(`${BASE_URL}/${id}`);
  } catch (error) {
    throw new Error("Error deleting family: " + (error as Error).message);
  }
}