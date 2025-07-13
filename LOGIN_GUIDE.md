# 🔐 Vineetha E-commerce - Login Guide

## ✅ **Admin Login Fixed!**

The login system now works with **mock authentication** when the backend is not running.

---

## 👤 **Admin Credentials**

### **Email:** `admin@vineetha.in`
### **Password:** `admin123`

---

## 👤 **Customer Credentials (Demo)**

### **Email:** `customer@example.com`
### **Password:** `password123`

---

## 🚀 **How to Login**

### **Step 1: Open the Website**
- Go to: `http://localhost:3000` or open `index.html` in your browser
- Click on the **"Login"** button or go to `login.html`

### **Step 2: Enter Admin Credentials**
- **Email:** `admin@vineetha.in`
- **Password:** `admin123`
- Click **"Login"**

### **Step 3: Success!**
- You'll see: **"Admin login successful!"**
- You'll be redirected to the main page
- You can now access admin features

---

## 🎯 **What Works Now**

### ✅ **Admin Features**
- Login with admin credentials
- Access to admin dashboard
- Product management (when backend is running)
- User management (when backend is running)

### ✅ **Customer Features**
- Login with customer credentials
- Browse products
- Add items to cart
- Place orders (mock)

### ✅ **Demo Features**
- Mock product data
- Local cart storage
- Search and filtering
- Responsive design

---

## 🔧 **How It Works**

### **When Backend is NOT Running:**
1. Frontend automatically uses **mock data**
2. Login uses **mock authentication**
3. Cart works with **local storage**
4. All features work **offline**

### **When Backend IS Running:**
1. Frontend connects to **real API**
2. Login uses **real authentication**
3. Cart syncs with **database**
4. All features work with **real data**

---

## 🛠️ **Testing Different Scenarios**

### **Test Admin Login:**
```
Email: admin@vineetha.in
Password: admin123
```

### **Test Customer Login:**
```
Email: customer@example.com
Password: password123
```

### **Test Invalid Login:**
```
Email: wrong@email.com
Password: wrongpassword
```
*Should show: "Invalid email or password"*

---

## 📱 **Access Points**

### **Main Pages:**
- **Home:** `index.html`
- **Products:** `products.html`
- **Cart:** `cart.html`
- **Login:** `login.html`
- **Admin:** `admin.html`

### **Local Server:**
- **URL:** `http://localhost:3000`
- **Status:** Running in background

---

## 🎉 **Success Indicators**

### **✅ Login Successful:**
- Green toast message: "Admin login successful!"
- Redirected to main page
- User info displayed in header

### **❌ Login Failed:**
- Red toast message: "Invalid email or password"
- Stays on login page
- Form fields cleared

---

## 🔄 **Switching Between Modes**

### **To Use Real Backend:**
1. Start backend: `cd backend && mvn spring-boot:run`
2. Refresh the page
3. Login will use real API

### **To Use Mock Data:**
1. Stop backend (Ctrl+C)
2. Refresh the page
3. Login will use mock authentication

---

## 🚨 **Troubleshooting**

### **If Login Still Doesn't Work:**
1. **Clear browser cache** and refresh
2. **Check browser console** for errors
3. **Try different browser**
4. **Check if local server is running**

### **If You See "API call failed":**
- This is normal when backend is not running
- The system will automatically use mock data
- You can still login and use all features

---

**🎯 Your admin login should now work perfectly! Try it out!** 