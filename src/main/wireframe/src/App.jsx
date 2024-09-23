import React from 'react'

import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Shopbycategory from './shopbycategory/Shopbycategory'
import LandingPage from './landingpage/Landingpage'


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
