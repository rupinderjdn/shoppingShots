import React from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import others2 from '../assets/img/hand4.jpg'; 
import hand3 from '../assets/img/hand3.webp'; 
import hos3 from '../assets/img/hand.webp'; 
import others1 from '../assets/img/hand5.webp';
export default function Handicraft() {
  // Array of product objects with name and imageSrc
  const products = [ { name: "Product 1", imageSrc: others2 }, { name: "Product 2", imageSrc: hand3 }, { name: "Product 3", imageSrc: hos3 }, { name: "Product 4", imageSrc: others1 }, ];

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    arrows: false,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        },
      },
    ],
  };

  return (
    <section className="py-16 bg-white">
      <div className="container mx-auto px-4 max-w-7xl">
        <h2 className="text-4xl font-bold mb-12 text-center">Handicrafts</h2>
        <Slider {...settings}>
          {products.map((product, index) => (
            <div key={index} className="px-2">
              <div className="bg-white rounded-lg shadow-lg overflow-hidden transition-transform duration-300 hover:scale-105">
                <div className="p-4">
                  <div className="aspect-square relative overflow-hidden rounded-md">
                    <img
                      src={product.imageSrc}
                      alt={product.name}
                      className="w-full h-full object-cover transition-transform duration-300 hover:scale-110"
                    />
                  </div>
                  <h3 className="font-semibold text-lg text-center mt-4">{product.name}</h3>
                </div>
              </div>
            </div>
          ))}
        </Slider>
        <div className="text-center mt-12">
          <button className="bg-primary text-primary-foreground hover:bg-primary/90 font-bold px-8 py-3 rounded-md transition-colors duration-300">
            Explore All Handicrafts
          </button>
        </div>
      </div>
    </section>
  );
}
