import React from 'react';
import { FaFacebook, FaInstagram } from 'react-icons/fa';
import { MdEmail } from 'react-icons/md';
import { Link } from 'react-router-dom';

export default function Footer() {
  return (
    <footer className="bg-gray-900 text-white py-8">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          <div>
            <h3 className="text-lg font-semibold mb-4">About Us</h3>
            <p className="text-gray-400">We are dedicated to providing the best shopping experience for our customers.</p>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">Quick Links</h3>
            <ul className="space-y-2">
              <li><Link to="/terms" className="text-gray-400 hover:text-white transition-colors duration-200">Terms & Conditions</Link></li>
              <li><Link to="/return-policy" className="text-gray-400 hover:text-white transition-colors duration-200">Return Policy</Link></li>
              <li><Link to="/shipping" className="text-gray-400 hover:text-white transition-colors duration-200">Shipping Details</Link></li>
              <li><Link to="/faq" className="text-gray-400 hover:text-white transition-colors duration-200">FAQ</Link></li>
            </ul>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">Contact Us</h3>
            <p className="text-gray-400">123 Fashion St, New York, NY 10001</p>
            <p className="text-gray-400">(123) 456-7890</p>
            <p className="text-gray-400">info@shoppingshots.com</p>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">Follow Us</h3>
            <div className="flex space-x-4">
              <a href="https://facebook.com" target="_blank" rel="noopener noreferrer" className="text-gray-400 hover:text-white transition-colors duration-200">
                <FaFacebook size={24} />
                <span className="sr-only">Facebook</span>
              </a>
              <a href="https://instagram.com" target="_blank" rel="noopener noreferrer" className="text-gray-400 hover:text-white transition-colors duration-200">
                <FaInstagram size={24} />
                <span className="sr-only">Instagram</span>
              </a>
              <a href="mailto:info@shoppingshots.com" className="text-gray-400 hover:text-white transition-colors duration-200">
                <MdEmail size={24} />
                <span className="sr-only">Email</span>
              </a>
            </div>
          </div>
        </div>
        <div className="text-center mt-8">
          <p className="text-gray-500">&copy; {new Date().getFullYear()} Shopping Shots. All rights reserved.</p>
        </div>
      </div>
    </footer>
  );
}
