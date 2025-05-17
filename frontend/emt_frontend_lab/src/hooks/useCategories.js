import {useMemo} from "react";

export const useCategories = () => {
    const categories = useMemo(() => [
        "ROOM",
        "HOUSE",
        "FLAT",
        "APARTMENT",
        "HOTEL",
        "MOTEL"
    ], []);

    return categories;
};