import axiosInstance from "../axios/axios.js";

const accommodationRepository = {
    findAll: async () => {
        return await axiosInstance.get("/bookings");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/bookings/${id}`);
    },
    add: async (data) => {
        return await axiosInstance.post("/bookings/add", data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/bookings/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/bookings/delete/${id}`);
    },
};

export default accommodationRepository;