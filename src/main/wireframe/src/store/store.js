import { configureStore } from "@reduxjs/toolkit";
import filteredproductReducer from "../slices/filteredproductslice"; // Import the reducer correctly

export const store = configureStore({
  reducer: {
    filtereddata: filteredproductReducer,
   
  },
});