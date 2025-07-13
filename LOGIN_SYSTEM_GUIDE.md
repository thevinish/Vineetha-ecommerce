# Vineetha E-commerce Login System Guide

## Overview
The Vineetha e-commerce platform now has a separate login system for users and administrators, providing better security and user experience.

## User Login System

### Access
- **URL**: `user-login.html`
- **Link**: Available in the main navigation menu as "Login"
- **Access**: Public - anyone can access

### Features
- **User Registration**: New customers can create accounts
- **User Login**: Existing customers can log in
- **Demo Credentials**: 
  - Email: `customer@vineetha.in`
  - Password: `customer123`

### User Experience
- Users are redirected to `products.html` after successful login
- Users can browse products, add items to cart, and place orders
- User authentication status is displayed in the navigation bar
- Users can logout by clicking on their name in the navigation

## Admin Login System

### Access
- **URL**: `login.html` (admin login page)
- **Direct Access**: `admin.html` (admin dashboard)
- **Access**: Restricted - only administrators can access

### Features
- **Admin-Only Access**: Restricted to administrators only
- **Secure Dashboard**: Full admin panel with product management
- **Demo Credentials**:
  - Email: `admin@vineetha.in`
  - Password: `admin123`

### Security Features
- **Role-Based Access Control**: Only users with ADMIN role can access
- **Automatic Redirect**: Non-admin users are redirected to login page
- **Access Denied Alert**: Clear message when unauthorized access is attempted

## Authentication Flow

### User Authentication
1. User visits `user-login.html`
2. User can either login or register
3. After successful login, user is redirected to `products.html`
4. User authentication status is displayed in navigation

### Admin Authentication
1. Admin visits `login.html` (admin login page)
2. Admin enters credentials
3. After successful login, admin is redirected to `admin.html`
4. Admin dashboard is accessible only to authenticated admins

### Role-Based Redirects
- **ADMIN role**: Redirected to `admin.html` (admin dashboard)
- **CUSTOMER role**: Redirected to `products.html` (shopping page)

## Security Implementation

### Frontend Security
- Role-based access control on admin pages
- Token-based authentication
- Automatic logout on session expiry
- Clear separation between user and admin interfaces

### Backend Integration
- JWT token authentication
- Role-based authorization
- Secure API endpoints
- Fallback to mock authentication when backend is unavailable

## File Structure

```
├── user-login.html          # User login and registration page
├── login.html              # Admin login page
├── admin.html              # Admin dashboard (restricted access)
├── products.html           # Product catalog (user access)
├── index.html              # Home page (public access)
└── script.js               # Authentication logic and API calls
```

## Testing the System

### Test User Login
1. Visit `user-login.html`
2. Use demo credentials: `customer@vineetha.in` / `customer123`
3. Should redirect to `products.html`
4. User name should appear in navigation

### Test Admin Login
1. Visit `login.html`
2. Use demo credentials: `admin@vineetha.in` / `admin123`
3. Should redirect to `admin.html`
4. Admin dashboard should be accessible

### Test Access Control
1. Try to access `admin.html` without logging in
2. Should show "Access Denied" and redirect to login
3. Try to access with user credentials
4. Should show "Access Denied" and redirect to login

## Backend Integration

The system integrates with the Spring Boot backend API:
- `/api/v1/auth/login` - User authentication
- `/api/v1/auth/register` - User registration
- JWT token-based security
- Role-based authorization

### Registration Data Structure
The registration form sends the following data to the backend:
```json
{
  "firstName": "John",
  "lastName": "Doe", 
  "email": "john@example.com",
  "password": "password123",
  "phoneNumber": "9876543210",
  "role": "CUSTOMER",
  "status": "ACTIVE"
}
```

### Error Handling
- **Backend Available**: Real registration with database storage
- **Backend Unavailable**: Fallback to mock registration for demo
- **Validation Errors**: Clear error messages from backend
- **Network Errors**: Graceful fallback with user-friendly messages

When the backend is unavailable, the system falls back to mock authentication for demo purposes.

## Notes

- The admin login page is intentionally separate from the user login
- Admin access is restricted and clearly marked
- User registration is enabled for new customers
- The system provides a smooth user experience with proper role-based navigation
- All authentication is handled securely with proper token management 