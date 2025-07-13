# Assets Documentation

This folder contains all the local assets used in the Vineetha e-commerce website.

## 📁 Folder Structure

```
assets/
├── images/
│   ├── products/     # Product images (20 files)
│   ├── categories/   # Category banner images (8 files)
│   └── banners/      # Carousel banner images (4 files)
├── icons/            # Custom SVG icons (7 files)
└── logos/            # Vineetha logo (1 file)
```

## 🖼️ Images

### Product Images (20 files)
- **Source**: Downloaded from Unsplash API
- **Format**: JPG
- **Size**: 400x400px (optimized)
- **Usage**: Product cards, admin dashboard

**Files:**
- iphone.jpg - iPhone product image
- samsung.jpg - Samsung phone image
- macbook.jpg - MacBook laptop image
- headphones.jpg - Sony headphones image
- nike-shoes.jpg - Nike shoes image
- adidas.jpg - Adidas shoes image
- watch.jpg - Apple Watch image
- ipad.jpg - iPad tablet image
- tv.jpg - Samsung TV image
- laptop.jpg - Dell laptop image
- camera.jpg - Canon camera image
- sunglasses.jpg - Ray-Ban sunglasses image
- coffee-maker.jpg - Breville coffee maker image
- kitchen.jpg - Kitchen appliances image
- books.jpg - Books collection image
- basketball.jpg - Wilson basketball image
- car.jpg - BMW car model image
- gaming.jpg - PlayStation gaming image
- fashion.jpg - Fashion items image

### Category Images (8 files)
- **Source**: Downloaded from Unsplash API
- **Format**: JPG
- **Size**: 300x200px (optimized)
- **Usage**: Category pages, featured categories

**Files:**
- electronics.jpg - Electronics category
- fashion.jpg - Fashion category
- home-kitchen.jpg - Home & Kitchen category
- beauty.jpg - Beauty & Personal Care category
- books.jpg - Books & Stationery category
- toys.jpg - Toys & Games category
- sports.jpg - Sports & Fitness category
- automotive.jpg - Automotive category

### Banner Images (4 files)
- **Source**: Downloaded from Unsplash API
- **Format**: JPG
- **Size**: 1200x400px (optimized)
- **Usage**: Carousel slider on home page

**Files:**
- offer-banner.jpg - Special offers banner
- delivery-banner.jpg - Free delivery banner
- security-banner.jpg - Secure shopping banner
- flash-sale-banner.jpg - Flash sale banner

## 🎨 Icons

### Custom SVG Icons (7 files)
- **Style**: Flipkart-inspired design
- **Format**: SVG
- **Usage**: Navigation, buttons, UI elements

**Files:**
- shopping-cart.svg - Shopping cart icon
- user.svg - User profile icon
- search.svg - Search icon
- heart.svg - Wishlist/heart icon
- star.svg - Rating star icon
- home.svg - Home icon
- grid.svg - Products grid icon

## 🏷️ Logos

### Vineetha Logo (1 file)
- **Style**: Custom gradient design
- **Format**: SVG
- **Colors**: Blue to orange gradient (#2874f0 to #fb641b)
- **Usage**: Website header, branding

**Files:**
- vineetha-logo.svg - Main Vineetha logo

## 📊 Asset Statistics

- **Total Files**: 39
- **Product Images**: 20
- **Category Images**: 8
- **Banner Images**: 4
- **Custom Icons**: 7
- **Logos**: 1

## 🔧 Usage in Code

### JavaScript (flipkart-script.js)
```javascript
const productImages = [
    'assets/images/products/iphone.jpg',
    'assets/images/products/samsung.jpg',
    // ... more images
];
```

### HTML
```html
<img src="assets/images/products/iphone.jpg" alt="iPhone">
<img src="assets/images/banners/offer-banner.jpg" alt="Special Offers">
```

### CSS
```css
.carousel-slide {
    background-image: url('assets/images/banners/offer-banner.jpg');
}
```

## 📝 Notes

- All images are optimized for web use
- SVG icons are scalable and lightweight
- Images are stored locally for better performance
- Original sources are from Unsplash (free stock photos)
- Custom icons follow Flipkart's design language 