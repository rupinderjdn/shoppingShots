// import React from 'react';
import Navbar from './Navbar';
import HeroCarousel from './HeroCrousal';
import NewArrivals from './NewArrivals';
import CategorySection from './Categorysection';
import SpecialOffer from './Specialoffer';
import Footer from './Footer';
import Handicraft from './Handicraft';
import Socks from './Socks';
//TODO jaspreet displayName and proptypes check are needed here
const LandingPage = () => {
  return (
    <>
      <Navbar />
    {/* // TODO  Jaspreet   these components should be generic and can be used in other pages as well */}
      <HeroCarousel />
      <NewArrivals />
      <CategorySection />
      <SpecialOffer />
      <Handicraft/>
      <Socks/>
      {/* // TODO Ends */}
      <Footer />
    </>
  );
};

export default LandingPage;