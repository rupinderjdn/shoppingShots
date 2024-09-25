// import React from 'react'

import './App.css'
import { Route, Routes } from 'react-router-dom';
import Shopbycategory from './shopbycategory/Shopbycategory'
import LandingPage from './landingpage/Landingpage'

//TODO jaspreet displayName and proptypes check are needed here
function App() {


  return (
    <>
      
     
      <Routes>
        <Route path="" element={<LandingPage/>} />
        <Route path="/shopbycategory" element={<Shopbycategory />} />
      </Routes>
  
    </>
  )
}

export default App
