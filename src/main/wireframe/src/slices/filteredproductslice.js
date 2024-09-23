import { createSlice } from '@reduxjs/toolkit';
const initialState = {
  filtereddata: "",
};
const filterproductSlice = createSlice({
  name: 'filtereddata',
  initialState,
  reducers: {
    setFiltereddata: (state, action) => {
        console.log(action.payload);
      state.filtereddata = action.payload;
      console.log(state.filtereddata)
    },
  },
});
export const {setFiltereddata} = filterproductSlice.actions;
export default filterproductSlice.reducer;