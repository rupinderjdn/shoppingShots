import React from 'react';
import { Link } from 'react-router-dom'; // Import Link from React Router
import { ChevronRight } from 'lucide-react';

const SpecialOffer = () => {
  return (
    <section className="py-12 bg-gray-800 text-white">
      <div className="container mx-auto px-4">
        <div className="flex flex-col md:flex-row items-center justify-between">
          <div className="md:w-1/2 mb-6 md:mb-0">
            <h2 className="text-3xl font-bold mb-4">Special Offer</h2>
            <p className="text-xl mb-6">Get 30% off on your first purchase. Use code: FIRST30</p>
            {/* Use React Router's Link */}
            <Link 
              to="/shop" 
              className="bg-white text-gray-800 px-6 py-2 rounded-full hover:bg-blue-900 hover:text-white hover:text-bold transition duration-300 inline-flex items-center"
            >
              Shop Now <ChevronRight className="ml-2 h-5 w-5" />
            </Link>
          </div>
          <div className="md:w-1/2">
            {/* Standard img tag for image */}
            <img 
              src="src\assets\img\others2.webp" 
              alt="Special Offer" 
              width={600} 
              height={300} 
              className="rounded-lg shadow-lg" 
            />
          </div>
        </div>
      </div>
    </section>
  );
};

export default SpecialOffer;
