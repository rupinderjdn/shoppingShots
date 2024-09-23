import React from 'react';
import { Link } from 'react-router-dom'; // Import Link from React Router
import hand3 from '../assets/img/hand3.webp'; 
import hos3 from '../assets/img/hos3.jpg'; 
import others from'../assets/img/others1.jpg'
const CategorySection = () => {
  const categories = [
    { name: 'Hoisery', imageSrc: hos3 },
    { name: 'Handicrafts', imageSrc: hand3 },
    { name: 'Others', imageSrc: others },
  ];

  return (
    <section className="py-12">
      <div className="container mx-auto px-4">
        <h2 className="text-3xl font-bold mb-8 text-center">Shop by Category</h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
          {categories.map((category) => (
            <div key={category.name} className="relative overflow-hidden rounded-lg shadow-md group">
              {/* Use <img> instead of Next.js Image component */}
              <img 
                src={category.imageSrc} 
                alt={`Image representing the category ${category.name}`} 
                width={600} 
                height={400} 
                className="w-full"
              />
              <div className="absolute inset-0 bg-black bg-opacity-40 flex items-center justify-center opacity-0 group-hover:opacity-100 transition duration-300">
                {/* Use React Router Link */}
                <Link to={`/category/${category.name.toLowerCase()}`} className="text-white text-2xl font-bold">
                  {category.name}
                </Link>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default CategorySection;
