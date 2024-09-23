import React from 'react';
import Navbar from './Navbar';
import HeroCarousel from './HeroCrousal';
import NewArrivals from './NewArrivals';
import CategorySection from './Categorysection';
import SpecialOffer from './Specialoffer';
import Footer from './Footer';
import Handicraft from './Handicraft';
import Socks from './Socks';

const LandingPage = () => {
  return (
    <>
      <Navbar />
      <HeroCarousel />
      <NewArrivals />
      <CategorySection />
      <SpecialOffer />
      <Handicraft/>
      <Socks/>
      <Footer />
    </>
  );
};

export default LandingPage;