import React from 'react'
import { useSelector } from 'react-redux'
const Produtslist = () => {
  const filteredProducts=useSelector((state)=>state.filtereddata.filtereddata)
  console.log( filteredProducts);
    const products = 
        filteredProducts        
      
    console.log(products)
    products.map((p)=>{
      console.log(p)
    })
return (
    <>
    <div className="bg-white">
      <div className=" max-w-2xl   lg:max-w-7xl lg:px-8">
        {/* <h2 className="text-2xl font-bold tracking-tight text-gray-900">All Products</h2> */}

        <div className="mt-6 grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:gap-x-8">
          {products.map((product) => (
            
            <div key={product.id} className="group relative">
              <div className="aspect-h-1 aspect-w-1 w-full h-fit overflow-hidden rounded-md bg-gray-200 lg:aspect-none group-hover:opacity-75 ">
                <img
                  alt={product.imageAlt}
                  src={product.imageSrc}
                  className="h-full w-full "
                />
              </div>
              <div className="mt-4 flex justify-between">
                <div>
                  <h3 className="text-sm text-gray-700">
                    <a href={product.href}>
                      <span aria-hidden="true" className="absolute inset-0" />
                      {product.name}
                    </a>
                  </h3>
                  <p className="mt-1 text-sm text-gray-500">{product.color}</p>
                </div>
                <p className="text-sm font-medium text-gray-900">{product.price}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  
</>
)
}

export default Produtslist