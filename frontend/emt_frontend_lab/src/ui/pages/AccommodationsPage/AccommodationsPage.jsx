import React, { useState } from 'react';
import { Box, Button, CircularProgress } from "@mui/material";
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import useAccommodations from "../../../hooks/useAccommodations.js";
import { useCategories } from "../../../hooks/useCategories.js";
import "./AccommodationsPage.css";
import AddAccommodationDialog from "../../components/accommodations/AddAccommodationDialog/AddAccommodationDialog.jsx";
import AccommodationsGrid from "../../components/accommodations/AccommodationsGrid/AccommodationsGrid.jsx";

const AccommodationsPage = () => {
    const { accommodations, loading, onAdd, onEdit, onDelete } = useAccommodations();
    const [addAccommodationDialogOpen, setAddAccommodationDialogOpen] = useState(false);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const categories = useCategories();

    const handleCategoryChange = (event, newCategory) => {
        setSelectedCategory(newCategory);
    };

    const filteredAccommodations = selectedCategory
        ? accommodations.filter((a) => a.category === selectedCategory)
        : accommodations;

    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress />
                    </Box>
                )}
                {!loading && (
                    <>
                        <Box sx={{ display: "flex", justifyContent: "space-between", mb: 2 }}>
                            <ToggleButtonGroup
                                color="primary"
                                value={selectedCategory}
                                exclusive
                                onChange={handleCategoryChange}
                                aria-label="Accommodation Category"
                            >
                                {categories.map((category) => (
                                    <ToggleButton key={category} value={category}>
                                        {category}
                                    </ToggleButton>
                                ))}
                            </ToggleButtonGroup>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => setAddAccommodationDialogOpen(true)}
                            >
                                Add Accommodation
                            </Button>
                        </Box>
                        <AccommodationsGrid
                            accommodations={filteredAccommodations}
                            onEdit={onEdit}
                            onDelete={onDelete}
                        />
                    </>
                )}
            </Box>
            <AddAccommodationDialog
                open={addAccommodationDialogOpen}
                onClose={() => setAddAccommodationDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default AccommodationsPage;
