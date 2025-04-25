import api from "./api";
import { LoginRequest, RegisterRequest, JwtResponse } from '../types/interfaces';

const API_URL = "/auth";

export const register = async (data: RegisterRequest): Promise<JwtResponse> => {
  try {
    const response = await api.post(`${API_URL}/register`, data);
    return response.data;
  } catch (error) {
    throw new Error("Error registering user: " + error);
  }
};

export const login = async (data: LoginRequest): Promise<JwtResponse> => {
  try {
    const response = await api.post(`${API_URL}/login`, data);
    return response.data;
  } catch (error) {
    throw new Error("Error logging in: " + error);
  }
};
