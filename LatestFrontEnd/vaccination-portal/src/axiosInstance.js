import axios from 'axios';

const baseURL = process.env.REACT_APP_API_URL;
// console.log(baseURL);

const axiosInstance = axios.create({
    baseURL,
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${sessionStorage.getItem('accessToken')}`
    }
});

const axiosInstanceWithoutAuth = axios.create({
    baseURL,
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json'
    }
});

// Function to make GET request
export const getWithoutAuth = async (url) => {
    try {
        const response = await axiosInstanceWithoutAuth.get(baseURL + url);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Function to make POST request
export const postWithAuth = async (url, data) => {
    try {
        const response = await axiosInstance.post(url, data);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Function to make PUT request
export const putWithAuth = async (url, data) => {
    try {
        const response = await axiosInstance.put(url, data);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Function to make DELETE request
export const deleteWithAuth = async (url) => {
    try {
        const response = await axiosInstance.delete(url);
        return response.data;
    } catch (error) {
        throw error;
    }
};