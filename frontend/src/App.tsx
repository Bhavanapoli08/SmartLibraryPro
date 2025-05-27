//import { useNavigate } from 'react-router-dom';
import { Routes, Route, Navigate } from 'react-router-dom';
import './App.css';
import { BookCheckoutPage } from './layouts/BookCheckoutPage/BookCheckoutPage';
import { HomePage } from './layouts/HomePage/HomePage';
import { Footer } from './layouts/NavbarAndFooter/Footer';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { SearchBooksPage } from './layouts/SearchBooksPage/SearchBooksPage';
import { ReviewListPage } from './layouts/BookCheckoutPage/ReviewListPage/ReviewListPage';
import { ShelfPage } from './layouts/ShelfPage/ShelfPage';
import { MessagesPage } from './layouts/MessagesPage/MessagesPage';
import { ManageLibraryPage } from './layouts/ManageLibraryPage/ManageLibraryPage';
import  Login  from './Auth/Login'
import { JSX } from 'react';

export default function App() {
  //const navigate = useNavigate();

  // TODO: Implement security later
  const isAuthenticated = true; // Placeholder - replace with actual auth check
  const PrivateRoute = ({ children }: { children: JSX.Element }) => {
    return isAuthenticated ? children : <Navigate to="/login" replace />;
  };

  return (
    <div className="d-flex flex-column min-vh-100">
      <Navbar />
      <div className="flex-grow-1">
        <Routes>
          <Route path="/" element={<Navigate to="/home" />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/search" element={<SearchBooksPage />} />
          <Route path="/reviewlist/:bookId" element={<ReviewListPage />} />
          <Route path="/checkout/:bookId" element={<BookCheckoutPage />} />
          
          {/* TODO: Implement login page later */}
          <Route path="/login" element={<Login />} />
          
          {/* Routes that will need protection later */}
          <Route path="/shelf" element={
            <PrivateRoute>
              <ShelfPage />
            </PrivateRoute>
          } />
          
          <Route path="/messages" element={
            <PrivateRoute>
              <MessagesPage />
            </PrivateRoute>
          } />
          
          <Route path="/admin" element={
            <PrivateRoute>
              <ManageLibraryPage />
            </PrivateRoute>
          } />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}