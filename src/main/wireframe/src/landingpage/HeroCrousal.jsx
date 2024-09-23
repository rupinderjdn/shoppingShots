import React from 'react';
import Slider from 'react-slick';
import { Link } from 'react-router-dom';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const HeroCarousel = () => {
  const sliderSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    arrows: false, // Remove default arrows
  };

  return (
    <section className="relative">
      <Slider {...sliderSettings}>
        {/* Slide 1 */}
        <div className="relative">
          <img 
            src="src/assets/img/handi.png" 
            alt="Slide 1" 
            width={1820} 
            height={500} 
            className="w-full h-auto"
          />
          <div className="absolute inset-0 flex flex-col justify-center items-center text-center text-white bg-black bg-opacity-40">
            <h1 className="text-4xl md:text-6xl font-bold mb-4">Handicraft Collection</h1>
            <p className="text-xl md:text-2xl mb-6">Up to 50% off on selected items</p>
            <Link 
              to="/shop" 
              className="bg-white text-gray-800 px-6 py-2 rounded-full hover:bg-gray-100 transition duration-300"
            >
              Shop Now
            </Link>
          </div>
        </div>

        {/* Slide 2 */}
        <div className="relative">
          <img 
            src="src/assets/img/sock.png" 
            alt="Slide 2" 
            width={1820} 
            height={500} 
            className="w-full h-auto"
          />
          <div className="absolute inset-0 flex flex-col justify-center items-center text-center text-white bg-black bg-opacity-40">
            <h1 className="text-4xl md:text-6xl font-bold mb-4">Latest Collection</h1>
            <p className="text-xl md:text-2xl mb-6">Check out our latest collection</p>
            <Link 
              to="/new-arrivals" 
              className="bg-white text-gray-800 px-6 py-2 rounded-full hover:bg-gray-100 transition duration-300"
            >
              Explore
            </Link>
          </div>
        </div>
      </Slider>
    </section>
  );
};

export default HeroCarousel;
