
import React from 'react'; // Importing images
//  import others2 from '/img/hand4.jpg'; 
//  import hand3 from '/img/hand3.webp'; 
//  import hos3 from '/img/hos3.jpg'; 
//  import others1 from '/img/hos1.jpg';

export default function NewArrivals() {
  
   const products = [ { name: "Product 1", imageSrc: '/img/hand4.jpg' }, { name: "Product 2", imageSrc: '/img/hand3.webp' }, { name: "Product 3", imageSrc: '/img/hos3.jpg' }, { name: "Product 4", imageSrc: '/img/hos1.jpg' }, ];
  return (
    <section className="py-16 bg-white">
      <div className="container mx-auto px-4 max-w-7xl">
        <h2 className="text-4xl font-bold mb-12 text-center">New Arrivals</h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8">
          {products.map((product, index) => (
            <div
              key={index}
              className="bg-yellow-100 rounded-lg shadow-md overflow-hidden transition-transform duration-300 hover:scale-105"
            >
              <div className="p-4">
                <div className="aspect-square relative overflow-hidden rounded-md">
                  <img
                    src={product.imageSrc}
                    alt={product.name}
                    className="w-full h-full object-cover transition-transform duration-300 hover:scale-110"
                    style={{ aspectRatio: '1/1' }} // For square images
                  />
                </div>
                <h3 className="font-semibold text-lg text-center mt-4">{product.name}</h3>
              </div>
            </div>
          ))}
        </div>
        <div className="text-center mt-2">
          <button className="bg-primary text-2xl   font-bold px-8 py-3 rounded-md ">
            Learn more
          </button>
        </div>
      </div>
    </section>
  );
}
