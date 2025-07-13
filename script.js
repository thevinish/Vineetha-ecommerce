// Vineetha-Inspired E-commerce JavaScript with Backend Integration

class VineethaEcommerce {
    constructor() {
        this.cart = JSON.parse(localStorage.getItem('vineethaCart')) || [];
        this.wishlist = JSON.parse(localStorage.getItem('vineethaWishlist')) || [];
        this.currentUser = JSON.parse(localStorage.getItem('vineethaUser')) || null;
        this.products = [];
        this.filters = {
            category: '',
            priceRange: { min: 0, max: 100000 },
            rating: 0,
            brand: [],
            availability: 'all'
        };
        this.sortBy = 'relevance';
        this.currentCarouselSlide = 0;
        this.apiBaseUrl = 'http://localhost:8080/api';
        this.token = localStorage.getItem('vineethaToken') || null;
        this.useMockData = false; // Flag to use mock data when backend is unavailable
        
        // Mock product data for fallback
        this.mockProducts = [
            {
                id: 1,
                name: "iPhone 14 Pro",
                description: "Latest iPhone with advanced camera system and A16 Bionic chip",
                brand: "Apple",
                price: 89999.00,
                originalPrice: 99999.00,
                stockQuantity: 50,
                category: "ELECTRONICS",
                status: "ACTIVE",
                mainImage: "assets/images/products/iphone.jpg",
                isFeatured: true,
                rating: 4.8,
                reviewCount: 125
            },
            {
                id: 2,
                name: "Nike Air Max 270",
                description: "Comfortable running shoes with Air Max technology",
                brand: "Nike",
                price: 8999.00,
                originalPrice: 10999.00,
                stockQuantity: 100,
                category: "CLOTHING",
                status: "ACTIVE",
                mainImage: "assets/images/products/nike-shoes.jpg",
                isFeatured: true,
                rating: 4.6,
                reviewCount: 89
            },
            {
                id: 3,
                name: "Sony WH-1000XM4",
                description: "Premium noise-cancelling headphones",
                brand: "Sony",
                price: 24999.00,
                originalPrice: 29999.00,
                stockQuantity: 30,
                category: "ELECTRONICS",
                status: "ACTIVE",
                mainImage: "assets/images/products/headphones.jpg",
                isFeatured: false,
                rating: 4.7,
                reviewCount: 156
            },
            {
                id: 4,
                name: "MacBook Pro M2",
                description: "Powerful laptop with Apple M2 chip",
                brand: "Apple",
                price: 149999.00,
                originalPrice: 159999.00,
                stockQuantity: 25,
                category: "ELECTRONICS",
                status: "ACTIVE",
                mainImage: "assets/images/products/laptop.jpg",
                isFeatured: true,
                rating: 4.9,
                reviewCount: 203
            },
            {
                id: 5,
                name: "Adidas Ultraboost",
                description: "High-performance running shoes",
                brand: "Adidas",
                price: 12999.00,
                originalPrice: 14999.00,
                stockQuantity: 75,
                category: "CLOTHING",
                status: "ACTIVE",
                mainImage: "assets/images/products/adidas.jpg",
                isFeatured: false,
                rating: 4.5,
                reviewCount: 67
            },
            {
                id: 6,
                name: "Samsung Galaxy S23",
                description: "Flagship Android smartphone with high-res display",
                brand: "Samsung",
                price: 79999.00,
                originalPrice: 89999.00,
                stockQuantity: 60,
                category: "ELECTRONICS",
                status: "ACTIVE",
                mainImage: "assets/images/products/samsung.jpg",
                isFeatured: false,
                rating: 4.4,
                reviewCount: 92
            },
            {
                id: 7,
                name: "Levi's 501 Jeans",
                description: "Classic straight fit jeans",
                brand: "Levi's",
                price: 2999.00,
                originalPrice: 3999.00,
                stockQuantity: 120,
                category: "CLOTHING",
                status: "ACTIVE",
                mainImage: "assets/images/products/fashion.jpg",
                isFeatured: false,
                rating: 4.3,
                reviewCount: 45
            },
            {
                id: 8,
                name: "The Alchemist",
                description: "Bestselling novel by Paulo Coelho",
                brand: "HarperCollins",
                price: 399.00,
                originalPrice: 499.00,
                stockQuantity: 80,
                category: "BOOKS",
                status: "ACTIVE",
                mainImage: "assets/images/products/books.jpg",
                isFeatured: false,
                rating: 4.8,
                reviewCount: 234
            }
        ];
        
        // Parse URL parameters for initial category filter
        this.parseUrlParameters();
        
        this.init();
    }

    parseUrlParameters() {
        const urlParams = new URLSearchParams(window.location.search);
        const category = urlParams.get('category');
        if (category) {
            // Map URL category values to display names
            const categoryMap = {
                'electronics': 'ELECTRONICS',
                'fashion': 'CLOTHING',
                'home': 'HOME_GARDEN',
                'beauty': 'BEAUTY_HEALTH',
                'books': 'BOOKS',
                'toys': 'TOYS_GAMES',
                'sports': 'SPORTS_OUTDOORS',
                'automotive': 'AUTOMOTIVE'
            };
            this.filters.category = categoryMap[category] || category;
        }
    }

    init() {
        this.setupEventListeners();
        this.loadProducts();
        this.getCart(); // Load cart data (works with both API and mock data)
        this.updateCartCount();
        this.setupSearchSuggestions();
        this.setupMobileMenu();
        this.setupAnimations();
        this.setupInfiniteScroll();
        this.setupCarousel();
        
        // Update UI for initial category filter
        if (this.filters.category) {
            this.updatePageForCategory(this.filters.category);
            this.updateActiveCategory(this.filters.category);
        }
    }

    // API Helper Methods
    async makeApiCall(endpoint, options = {}) {
        const url = `${this.apiBaseUrl}${endpoint}`;
        const defaultOptions = {
            headers: {
                'Content-Type': 'application/json',
                ...(this.token && { 'Authorization': `Bearer ${this.token}` })
            }
        };

        try {
            const response = await fetch(url, { ...defaultOptions, ...options });
            
            if (!response.ok) {
                const errorData = await response.json().catch(() => ({}));
                throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('API call failed:', error);
            // Set flag to use mock data for future calls
            this.useMockData = true;
            throw error;
        }
    }

    // Authentication Methods
    async login(email, password) {
        // Admin credentials
        const adminCredentials = {
            email: 'admin@vineetha.in',
            password: 'admin123',
            user: {
                id: 1,
                email: 'admin@vineetha.in',
                firstName: 'Admin',
                lastName: 'User',
                role: 'ADMIN',
                status: 'ACTIVE'
            }
        };

        // Customer demo credentials
        const customerCredentials = {
            email: 'customer@vineetha.in',
            password: 'customer123',
            user: {
                id: 2,
                email: 'customer@vineetha.in',
                firstName: 'Demo',
                lastName: 'Customer',
                role: 'CUSTOMER',
                status: 'ACTIVE'
            }
        };

        // Check if using mock data or if API fails
        if (this.useMockData || !this.token) {
            // Mock authentication for both admin and customer
            if (email === adminCredentials.email && password === adminCredentials.password) {
                this.token = 'mock-admin-token-' + Date.now();
                this.currentUser = adminCredentials.user;
                localStorage.setItem('vineethaToken', this.token);
                localStorage.setItem('vineethaUser', JSON.stringify(this.currentUser));
                localStorage.setItem('userRole', 'ADMIN');
                this.showToast('Admin login successful!', 'success');
                return { token: this.token, user: this.currentUser };
            } else if (email === customerCredentials.email && password === customerCredentials.password) {
                this.token = 'mock-customer-token-' + Date.now();
                this.currentUser = customerCredentials.user;
                localStorage.setItem('vineethaToken', this.token);
                localStorage.setItem('vineethaUser', JSON.stringify(this.currentUser));
                localStorage.setItem('userRole', 'CUSTOMER');
                this.showToast('Customer login successful!', 'success');
                return { token: this.token, user: this.currentUser };
            } else {
                throw new Error('Invalid credentials. Try admin@vineetha.in / admin123 or customer@vineetha.in / customer123');
            }
        }

        // Try real API login
        try {
            const response = await this.makeApiCall('/v1/auth/login', {
                method: 'POST',
                body: JSON.stringify({ email, password })
            });

            if (response.success) {
                this.token = response.token;
                this.currentUser = response.user;
                
                localStorage.setItem('vineethaToken', this.token);
                localStorage.setItem('vineethaUser', JSON.stringify(this.currentUser));
                localStorage.setItem('userRole', response.user.role);
                
                return response;
            } else {
                throw new Error(response.message || 'Login failed');
            }
        } catch (error) {
            throw new Error('Login failed: ' + error.message);
        }
    }

    async register(userData) {
        // Try real API registration
        try {
            const response = await this.makeApiCall('/v1/auth/register', {
                method: 'POST',
                body: JSON.stringify(userData)
            });
            
            if (response.success) {
                return response;
            } else {
                throw new Error(response.message || 'Registration failed');
            }
        } catch (error) {
            // For demo purposes, simulate successful registration
            console.log('Registration simulation:', userData);
            return { success: true, message: 'Registration successful! Please login.' };
        }
    }

    logout() {
        this.token = null;
        this.currentUser = null;
        this.cart = [];
        
        localStorage.removeItem('vineethaToken');
        localStorage.removeItem('vineethaUser');
        localStorage.removeItem('vineethaCart');
        
        window.location.href = 'index.html';
    }

    // Product Methods
    async loadProducts(page = 0, size = 20) {
        try {
            let url = `/products?page=${page}&size=${size}`;
            
            // Add category filter if specified
            if (this.filters.category) {
                url += `&category=${this.filters.category}`;
            }

            const response = await this.makeApiCall(url);
            
            if (page === 0) {
                this.products = response.content || response;
            } else {
                this.products = [...this.products, ...(response.content || response)];
            }

            this.useMockData = false;
            this.renderProducts();
        } catch (error) {
            console.error('Error loading products from API, using mock data:', error);
            
            // Use mock data as fallback
            this.useMockData = true;
            this.products = this.mockProducts;
            
            // Apply category filter to mock data
            if (this.filters.category) {
                this.products = this.products.filter(product => 
                    product.category === this.filters.category
                );
            }
            
            this.renderProducts();
            this.showToast('Using demo data (backend not available)', 'info');
        }
    }

    async getProductById(productId) {
        if (this.useMockData) {
            // Return mock product data
            const product = this.mockProducts.find(p => p.id === parseInt(productId));
            if (product) {
                return product;
            } else {
                throw new Error('Product not found');
            }
        }

        try {
            const response = await this.makeApiCall(`/products/${productId}`);
            return response;
        } catch (error) {
            console.error('Error loading product:', error);
            throw error;
        }
    }

    // Cart Methods
    async getCart() {
        if (this.useMockData) {
            // Use local cart data when using mock data
            this.cart = JSON.parse(localStorage.getItem('vineethaCart')) || [];
            this.updateCartCount();
            this.renderCart();
            return;
        }

        if (!this.token) return;

        try {
            const response = await this.makeApiCall('/cart');
            this.cart = response.items || [];
            this.updateCartCount();
            this.renderCart();
        } catch (error) {
            console.error('Error loading cart:', error);
        }
    }

    async addToCart(productId, quantity = 1) {
        if (this.useMockData) {
            // Handle cart locally when using mock data
            const existingItem = this.cart.find(item => item.productId === productId);
            if (existingItem) {
                existingItem.quantity += quantity;
            } else {
                const product = this.products.find(p => p.id === productId);
                if (product) {
                    this.cart.push({
                        id: Date.now(),
                        productId: productId,
                        product: product,
                        quantity: quantity,
                        unitPrice: product.price
                    });
                }
            }
            localStorage.setItem('vineethaCart', JSON.stringify(this.cart));
            this.updateCartCount();
            this.renderCart();
            this.showToast('Item added to cart!', 'success');
            this.animateAddToCart(productId);
            return;
        }

        if (!this.token) {
            this.showToast('Please login to add items to cart', 'warning');
            return;
        }

        try {
            const response = await this.makeApiCall('/cart/add', {
                method: 'POST',
                body: JSON.stringify({ productId, quantity })
            });

            await this.getCart();
            this.showToast('Item added to cart!', 'success');
            this.animateAddToCart(productId);
        } catch (error) {
            this.showToast('Failed to add item to cart', 'error');
        }
    }

    async updateCartItem(productId, quantity) {
        if (!this.token) return;

        try {
            await this.makeApiCall('/cart/update', {
                method: 'PUT',
                body: JSON.stringify({ productId, quantity })
            });

            await this.getCart();
        } catch (error) {
            this.showToast('Failed to update cart', 'error');
        }
    }

    async removeFromCart(productId) {
        if (this.useMockData) {
            // Handle cart removal locally when using mock data
            this.cart = this.cart.filter(item => item.productId !== productId);
            localStorage.setItem('vineethaCart', JSON.stringify(this.cart));
            this.updateCartCount();
            this.renderCart();
            this.showToast('Item removed from cart', 'success');
            return;
        }

        if (!this.token) return;

        try {
            await this.makeApiCall(`/cart/remove/${productId}`, {
                method: 'DELETE'
            });

            await this.getCart();
            this.showToast('Item removed from cart', 'success');
        } catch (error) {
            this.showToast('Failed to remove item', 'error');
        }
    }

    // Order Methods
    async createOrder(orderData) {
        if (!this.token) {
            this.showToast('Please login to place an order', 'warning');
            return;
        }

        try {
            const response = await this.makeApiCall('/orders', {
                method: 'POST',
                body: JSON.stringify(orderData)
            });

            this.showToast('Order placed successfully!', 'success');
            await this.getCart(); // Refresh cart
            
            return response;
        } catch (error) {
            this.showToast('Failed to place order: ' + error.message, 'error');
            throw error;
        }
    }

    async getOrders() {
        if (!this.token) return [];

        try {
            const response = await this.makeApiCall('/orders');
            return response;
        } catch (error) {
            console.error('Error loading orders:', error);
            return [];
        }
    }

    // Search Methods
    async searchProducts(query) {
        try {
            const response = await this.makeApiCall(`/products/search?q=${encodeURIComponent(query)}`);
            this.products = response.content || response;
            this.renderProducts();
        } catch (error) {
            console.error('Error searching products:', error);
            this.showToast('Search failed', 'error');
        }
    }

    setupEventListeners() {
        // Search functionality
        const searchInput = document.querySelector('.search-input');
        if (searchInput) {
            searchInput.addEventListener('input', (e) => this.handleSearch(e.target.value));
            searchInput.addEventListener('focus', () => this.showSearchSuggestions());
            searchInput.addEventListener('blur', () => setTimeout(() => this.hideSearchSuggestions(), 200));
        }

        // Search button
        const searchBtn = document.querySelector('.search-btn');
        if (searchBtn) {
            searchBtn.addEventListener('click', () => this.performSearch());
        }

        // Cart functionality
        document.addEventListener('click', (e) => {
            if (e.target.classList.contains('add-to-cart-btn')) {
                e.preventDefault();
                const productId = e.target.dataset.productId;
                this.addToCart(productId);
            }

            if (e.target.classList.contains('wishlist-btn')) {
                e.preventDefault();
                const productId = e.target.dataset.productId;
                this.toggleWishlist(productId);
            }

            if (e.target.classList.contains('remove-from-cart')) {
                e.preventDefault();
                const productId = e.target.dataset.productId;
                this.removeFromCart(productId);
            }
        });

        // Filter functionality
        document.addEventListener('change', (e) => {
            if (e.target.classList.contains('filter-checkbox')) {
                this.applyFilters();
            }

            if (e.target.classList.contains('sort-select')) {
                this.sortBy = e.target.value;
                this.renderProducts();
            }
        });

        // Price range filter
        const priceInputs = document.querySelectorAll('.price-input');
        priceInputs.forEach(input => {
            input.addEventListener('change', () => this.applyFilters());
        });

        // Category navigation
        document.addEventListener('click', (e) => {
            if (e.target.closest('.category-item')) {
                e.preventDefault();
                const category = e.target.closest('.category-item').dataset.category;
                this.filterByCategory(category);
            }
        });

        // Product card interactions
        document.addEventListener('click', (e) => {
            if (e.target.closest('.product-card')) {
                const productCard = e.target.closest('.product-card');
                if (!e.target.classList.contains('add-to-cart-btn') && 
                    !e.target.classList.contains('wishlist-btn')) {
                    const productId = productCard.dataset.productId;
                    this.openProductDetails(productId);
                }
            }
        });

        // Form submissions
        const loginForm = document.getElementById('loginForm');
        if (loginForm) {
            loginForm.addEventListener('submit', (e) => this.handleLogin(e));
        }

        const registerForm = document.getElementById('registerForm');
        if (registerForm) {
            registerForm.addEventListener('submit', (e) => this.handleRegister(e));
        }

        // Checkout functionality
        const checkoutForm = document.getElementById('checkoutForm');
        if (checkoutForm) {
            checkoutForm.addEventListener('submit', (e) => this.handleCheckout(e));
        }

        // Admin form submissions
        const productUploadForm = document.getElementById('productUploadForm');
        if (productUploadForm) {
            productUploadForm.addEventListener('submit', (e) => this.handleProductUpload(e));
        }

        // Logout functionality
        const logoutBtn = document.querySelector('.logout-btn');
        if (logoutBtn) {
            logoutBtn.addEventListener('click', (e) => {
                e.preventDefault();
                this.logout();
            });
        }
    }

    setupCarousel() {
        const carousel = document.querySelector('.carousel');
        if (!carousel) return;

        const slides = carousel.querySelectorAll('.carousel-slide');
        const prevBtn = carousel.querySelector('.carousel-prev');
        const nextBtn = carousel.querySelector('.carousel-next');
        const indicators = carousel.querySelectorAll('.carousel-indicator');

        if (prevBtn) {
            prevBtn.addEventListener('click', () => this.prevSlide());
        }

        if (nextBtn) {
            nextBtn.addEventListener('click', () => this.nextSlide());
        }

        indicators.forEach((indicator, index) => {
            indicator.addEventListener('click', () => this.goToSlide(index));
        });

        // Auto-play carousel
        setInterval(() => this.nextSlide(), 5000);
    }

    nextSlide() {
        const carousel = document.querySelector('.carousel');
        if (!carousel) return;

        const slides = carousel.querySelectorAll('.carousel-slide');
        const indicators = carousel.querySelectorAll('.carousel-indicator');
        
        slides[this.currentCarouselSlide].classList.remove('active');
        indicators[this.currentCarouselSlide].classList.remove('active');
        
        this.currentCarouselSlide = (this.currentCarouselSlide + 1) % slides.length;
        
        slides[this.currentCarouselSlide].classList.add('active');
        indicators[this.currentCarouselSlide].classList.add('active');
    }

    prevSlide() {
        const carousel = document.querySelector('.carousel');
        if (!carousel) return;

        const slides = carousel.querySelectorAll('.carousel-slide');
        const indicators = carousel.querySelectorAll('.carousel-indicator');
        
        slides[this.currentCarouselSlide].classList.remove('active');
        indicators[this.currentCarouselSlide].classList.remove('active');
        
        this.currentCarouselSlide = this.currentCarouselSlide === 0 ? slides.length - 1 : this.currentCarouselSlide - 1;
        
        slides[this.currentCarouselSlide].classList.add('active');
        indicators[this.currentCarouselSlide].classList.add('active');
    }

    goToSlide(index) {
        const carousel = document.querySelector('.carousel');
        if (!carousel) return;

        const slides = carousel.querySelectorAll('.carousel-slide');
        const indicators = carousel.querySelectorAll('.carousel-indicator');
        
        slides[this.currentCarouselSlide].classList.remove('active');
        indicators[this.currentCarouselSlide].classList.remove('active');
        
        this.currentCarouselSlide = index;
        
        slides[this.currentCarouselSlide].classList.add('active');
        indicators[this.currentCarouselSlide].classList.add('active');
    }

    setupMobileMenu() {
        const hamburger = document.querySelector('.hamburger');
        const navMenu = document.querySelector('.nav-menu');
        
        if (hamburger && navMenu) {
            hamburger.addEventListener('click', () => {
                hamburger.classList.toggle('active');
                navMenu.classList.toggle('active');
                
                // Animate hamburger bars
                const bars = hamburger.querySelectorAll('.bar');
                bars.forEach((bar, index) => {
                    if (hamburger.classList.contains('active')) {
                        if (index === 0) bar.style.transform = 'rotate(45deg) translate(5px, 5px)';
                        if (index === 1) bar.style.opacity = '0';
                        if (index === 2) bar.style.transform = 'rotate(-45deg) translate(7px, -6px)';
                    } else {
                        bar.style.transform = 'none';
                        bar.style.opacity = '1';
                    }
                });
            });
        }
    }

    setupAnimations() {
        // Intersection Observer for fade-in animations
        const observerOptions = {
            threshold: 0.1,
            rootMargin: '0px 0px -50px 0px'
        };

        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('fade-in-up');
                }
            });
        }, observerOptions);

        // Observe product cards and sections
        document.querySelectorAll('.product-card, .products-section, .hero-banner').forEach(el => {
            observer.observe(el);
        });

        // Add hover animations to product cards
        document.querySelectorAll('.product-card').forEach(card => {
            card.addEventListener('mouseenter', () => {
                card.style.transform = 'translateY(-8px) scale(1.02)';
            });

            card.addEventListener('mouseleave', () => {
                card.style.transform = 'translateY(0) scale(1)';
            });
        });
    }

    setupInfiniteScroll() {
        let isLoading = false;
        let page = 0;

        const loadMoreProducts = () => {
            if (isLoading) return;
            
            isLoading = true;
            this.showLoading();

            this.loadProducts(page, 20).then(() => {
                page++;
                isLoading = false;
                this.hideLoading();
            }).catch(() => {
                isLoading = false;
                this.hideLoading();
            });
        };

        // Intersection Observer for infinite scroll
        const loadMoreTrigger = document.querySelector('.load-more-trigger');
        if (loadMoreTrigger) {
            const observer = new IntersectionObserver((entries) => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        loadMoreProducts();
                    }
                });
            });

            observer.observe(loadMoreTrigger);
        }
    }

    setupSearchSuggestions() {
        const searchInput = document.querySelector('.search-input');
        if (!searchInput) return;

        // Create suggestions container
        const suggestionsContainer = document.createElement('div');
        suggestionsContainer.className = 'search-suggestions';
        suggestionsContainer.style.cssText = `
            position: absolute;
            top: 100%;
            left: 0;
            right: 0;
            background: white;
            border: 1px solid var(--border-color);
            border-top: none;
            border-radius: 0 0 4px 4px;
            box-shadow: var(--shadow-medium);
            z-index: 1000;
            max-height: 300px;
            overflow-y: auto;
            display: none;
        `;

        searchInput.parentElement.style.position = 'relative';
        searchInput.parentElement.appendChild(suggestionsContainer);

        // Sample suggestions
        const suggestions = [
            'iPhone 15 Pro Max',
            'Samsung Galaxy S24',
            'MacBook Air M3',
            'Sony WH-1000XM5',
            'Nike Air Max',
            'Adidas Ultraboost',
            'Apple Watch Series 9',
            'iPad Pro 12.9',
            'Samsung QLED TV',
            'Dell XPS 13'
        ];

        searchInput.addEventListener('input', (e) => {
            const query = e.target.value.toLowerCase();
            if (query.length < 2) {
                suggestionsContainer.style.display = 'none';
                return;
            }

            const filteredSuggestions = suggestions.filter(suggestion => 
                suggestion.toLowerCase().includes(query)
            );

            if (filteredSuggestions.length > 0) {
                suggestionsContainer.innerHTML = filteredSuggestions
                    .map(suggestion => `
                        <div class="suggestion-item" style="padding: 12px 16px; cursor: pointer; border-bottom: 1px solid #f0f0f0;">
                            <i class="fas fa-search" style="color: #878787; margin-right: 12px;"></i>
                            ${suggestion}
                        </div>
                    `).join('');
                suggestionsContainer.style.display = 'block';
            } else {
                suggestionsContainer.style.display = 'none';
            }
        });

        // Handle suggestion clicks
        suggestionsContainer.addEventListener('click', (e) => {
            if (e.target.classList.contains('suggestion-item')) {
                const suggestion = e.target.textContent.trim();
                searchInput.value = suggestion;
                suggestionsContainer.style.display = 'none';
                this.performSearch();
            }
        });
    }

    showSearchSuggestions() {
        const suggestions = document.querySelector('.search-suggestions');
        if (suggestions) {
            suggestions.style.display = 'block';
        }
    }

    hideSearchSuggestions() {
        const suggestions = document.querySelector('.search-suggestions');
        if (suggestions) {
            suggestions.style.display = 'none';
        }
    }

    renderProducts() {
        const productsContainer = document.querySelector('.products-grid');
        if (!productsContainer) return;

        let filteredProducts = this.filterProducts();
        filteredProducts = this.sortProducts(filteredProducts);

        const productsHTML = filteredProducts.map(product => this.createProductCard(product)).join('');
        productsContainer.innerHTML = productsHTML;

        // Update product count
        const productCount = document.getElementById('product-count');
        if (productCount) {
            productCount.textContent = filteredProducts.length;
        }

        // Re-attach event listeners
        this.setupAnimations();
    }

    createProductCard(product) {
        const discount = product.originalPrice ? Math.round(((product.originalPrice - product.price) / product.originalPrice) * 100) : 0;
        const badgesHTML = product.badges ? product.badges.map(badge => 
            `<span class="product-badge badge-${badge.type}">${badge.text}</span>`
        ).join('') : '';

        return `
            <div class="product-card" data-product-id="${product.id}">
                <div class="product-image">
                    <img src="${product.mainImage || product.image || 'assets/images/products/default.jpg'}" alt="${product.name}" loading="lazy">
                    <div class="product-badges">
                        ${badgesHTML}
                        ${discount > 0 ? `<span class="product-badge badge-offer">${discount}% OFF</span>` : ''}
                    </div>
                </div>
                <div class="product-details">
                    <h3 class="product-title">${product.name}</h3>
                    <div class="product-price">
                        <span class="current-price">₹${product.price.toLocaleString()}</span>
                        ${product.originalPrice ? `<span class="original-price">₹${product.originalPrice.toLocaleString()}</span>` : ''}
                        ${discount > 0 ? `<span class="discount">${discount}% off</span>` : ''}
                    </div>
                    <div class="product-rating">
                        <div class="rating-stars">
                            ${this.generateStars(product.rating || 0)}
                        </div>
                        <span class="rating-text">(${product.reviewCount || 0})</span>
                    </div>
                    <div class="product-actions">
                        <button class="add-to-cart-btn" data-product-id="${product.id}">
                            <i class="fas fa-shopping-cart"></i>
                            Add to Cart
                        </button>
                        <button class="wishlist-btn" data-product-id="${product.id}">
                            <i class="fas fa-heart"></i>
                        </button>
                    </div>
                </div>
            </div>
        `;
    }

    generateStars(rating) {
        const fullStars = Math.floor(rating);
        const hasHalfStar = rating % 1 !== 0;
        const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

        let starsHTML = '';
        
        for (let i = 0; i < fullStars; i++) {
            starsHTML += '<i class="fas fa-star star"></i>';
        }
        
        if (hasHalfStar) {
            starsHTML += '<i class="fas fa-star-half-alt star"></i>';
        }
        
        for (let i = 0; i < emptyStars; i++) {
            starsHTML += '<i class="far fa-star star"></i>';
        }

        return starsHTML;
    }

    filterProducts() {
        return this.products.filter(product => {
            // Category filter
            if (this.filters.category && product.category !== this.filters.category) {
                return false;
            }

            // Price range filter
            if (product.price < this.filters.priceRange.min || product.price > this.filters.priceRange.max) {
                return false;
            }

            // Rating filter
            if (this.filters.rating > 0 && (product.rating || 0) < this.filters.rating) {
                return false;
            }

            // Brand filter
            if (this.filters.brand.length > 0 && !this.filters.brand.includes(product.brand)) {
                return false;
            }

            // Availability filter
            if (this.filters.availability === 'inStock' && product.stockQuantity <= 0) {
                return false;
            }

            return true;
        });
    }

    sortProducts(products) {
        switch (this.sortBy) {
            case 'price-low':
                return products.sort((a, b) => a.price - b.price);
            case 'price-high':
                return products.sort((a, b) => b.price - a.price);
            case 'rating':
                return products.sort((a, b) => (b.rating || 0) - (a.rating || 0));
            case 'newest':
                return products.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
            default:
                return products;
        }
    }

    toggleWishlist(productId) {
        const index = this.wishlist.indexOf(productId);
        
        if (index > -1) {
            this.wishlist.splice(index, 1);
            this.showToast('Removed from wishlist', 'success');
        } else {
            this.wishlist.push(productId);
            this.showToast('Added to wishlist', 'success');
        }

        localStorage.setItem('vineethaWishlist', JSON.stringify(this.wishlist));
        this.updateWishlistUI(productId);
    }

    updateWishlistUI(productId) {
        const wishlistBtn = document.querySelector(`[data-product-id="${productId}"].wishlist-btn`);
        if (wishlistBtn) {
            const isInWishlist = this.wishlist.includes(productId);
            const icon = wishlistBtn.querySelector('i');
            
            if (isInWishlist) {
                icon.className = 'fas fa-heart';
                wishlistBtn.style.color = 'var(--danger-color)';
            } else {
                icon.className = 'far fa-heart';
                wishlistBtn.style.color = 'var(--text-secondary)';
            }
        }
    }

    animateAddToCart(productId) {
        const productCard = document.querySelector(`[data-product-id="${productId}"]`);
        if (!productCard) return;

        const cartIcon = document.querySelector('.nav-link[href="cart.html"]');
        if (!cartIcon) return;

        // Create flying element
        const flyingElement = document.createElement('div');
        flyingElement.innerHTML = '<i class="fas fa-shopping-cart"></i>';
        flyingElement.style.cssText = `
            position: fixed;
            z-index: 10000;
            color: var(--primary-color);
            font-size: 20px;
            pointer-events: none;
            transition: all 0.8s cubic-bezier(0.25, 0.46, 0.45, 0.94);
        `;

        // Get positions
        const cardRect = productCard.getBoundingClientRect();
        const cartRect = cartIcon.getBoundingClientRect();

        // Set initial position
        flyingElement.style.left = cardRect.left + cardRect.width / 2 + 'px';
        flyingElement.style.top = cardRect.top + cardRect.height / 2 + 'px';

        document.body.appendChild(flyingElement);

        // Animate to cart
        setTimeout(() => {
            flyingElement.style.left = cartRect.left + cartRect.width / 2 + 'px';
            flyingElement.style.top = cartRect.top + cartRect.height / 2 + 'px';
            flyingElement.style.transform = 'scale(0.3)';
            flyingElement.style.opacity = '0';
        }, 50);

        // Remove element after animation
        setTimeout(() => {
            document.body.removeChild(flyingElement);
        }, 850);
    }

    updateCartCount() {
        const cartCount = document.querySelector('.cart-count');
        if (cartCount) {
            const totalItems = this.cart.reduce((sum, item) => sum + item.quantity, 0);
            cartCount.textContent = totalItems;
            cartCount.style.display = totalItems > 0 ? 'block' : 'none';
        }
    }

    renderCart() {
        const cartContainer = document.querySelector('.cart-items-container');
        if (!cartContainer) return;

        if (this.cart.length === 0) {
            cartContainer.innerHTML = `
                <div class="empty-cart">
                    <i class="fas fa-shopping-cart"></i>
                    <h3>Your cart is empty</h3>
                    <p>Add some products to get started!</p>
                    <a href="products.html" class="btn btn-primary">Continue Shopping</a>
                </div>
            `;
            return;
        }

        const cartHTML = this.cart.map(item => `
            <div class="cart-item" data-product-id="${item.productId || item.id}">
                <div class="cart-item-image">
                    <img src="${item.product?.mainImage || item.image}" alt="${item.product?.name || item.name}">
                </div>
                <div class="cart-item-details">
                    <h4>${item.product?.name || item.name}</h4>
                    <p class="cart-item-price">₹${(item.unitPrice || item.price).toLocaleString()}</p>
                    <div class="cart-item-quantity">
                        <button class="quantity-btn" onclick="app.updateQuantity('${item.productId || item.id}', -1)">-</button>
                        <span>${item.quantity}</span>
                        <button class="quantity-btn" onclick="app.updateQuantity('${item.productId || item.id}', 1)">+</button>
                    </div>
                </div>
                <button class="remove-from-cart" data-product-id="${item.productId || item.id}">
                    <i class="fas fa-trash"></i>
                </button>
            </div>
        `).join('');

        cartContainer.innerHTML = cartHTML;
        this.updateCartTotal();
    }

    updateQuantity(productId, change) {
        const newQuantity = this.cart.find(item => (item.productId || item.id) === productId)?.quantity + change;
        
        if (newQuantity <= 0) {
            this.removeFromCart(productId);
        } else {
            this.updateCartItem(productId, newQuantity);
        }
    }

    updateCartTotal() {
        const total = this.cart.reduce((sum, item) => sum + ((item.unitPrice || item.price) * item.quantity), 0);
        const totalElement = document.querySelector('.cart-total');
        if (totalElement) {
            totalElement.textContent = `₹${total.toLocaleString()}`;
        }
    }

    handleSearch(query) {
        // Implement search functionality
        console.log('Searching for:', query);
    }

    performSearch() {
        const searchInput = document.querySelector('.search-input');
        if (searchInput) {
            const query = searchInput.value.trim();
            if (query) {
                this.searchProducts(query);
            }
        }
    }

    filterByCategory(category) {
        this.filters.category = category;
        this.loadProducts(0, 20); // Reset to first page
        
        // Update page title and breadcrumb to reflect selected category
        this.updatePageForCategory(category);
        
        // Update active category in navigation
        this.updateActiveCategory(category);
    }

    updatePageForCategory(category) {
        const pageTitle = document.querySelector('.page-title');
        const breadcrumb = document.querySelector('.breadcrumb span');
        
        if (pageTitle) {
            pageTitle.textContent = category ? `${category} Products` : 'All Products';
        }
        
        if (breadcrumb) {
            breadcrumb.textContent = category || 'Products';
        }
    }

    updateActiveCategory(category) {
        // Remove active class from all category items
        document.querySelectorAll('.category-item').forEach(item => {
            item.classList.remove('active');
        });
        
        // Add active class to selected category
        if (category) {
            const categoryItem = document.querySelector(`[data-category="${category}"]`);
            if (categoryItem) {
                categoryItem.classList.add('active');
            }
        }
    }

    applyFilters() {
        // Get selected categories from checkboxes
        const categoryCheckboxes = document.querySelectorAll('.filter-checkbox[value*="ELECTRONICS"], .filter-checkbox[value*="CLOTHING"], .filter-checkbox[value*="HOME_GARDEN"], .filter-checkbox[value*="BEAUTY_HEALTH"], .filter-checkbox[value*="BOOKS"], .filter-checkbox[value*="TOYS_GAMES"], .filter-checkbox[value*="SPORTS_OUTDOORS"], .filter-checkbox[value*="AUTOMOTIVE"]');
        const selectedCategories = Array.from(categoryCheckboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value);
        
        // Get selected brands
        const brandCheckboxes = document.querySelectorAll('.filter-checkbox[value*="Apple"], .filter-checkbox[value*="Samsung"], .filter-checkbox[value*="Nike"], .filter-checkbox[value*="Adidas"]');
        const selectedBrands = Array.from(brandCheckboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value);
        
        // Get price range
        const minPriceInput = document.querySelector('.price-input[placeholder="Min"]');
        const maxPriceInput = document.querySelector('.price-input[placeholder="Max"]');
        const minPrice = minPriceInput ? parseFloat(minPriceInput.value) || 0 : 0;
        const maxPrice = maxPriceInput ? parseFloat(maxPriceInput.value) || 100000 : 100000;
        
        // Get rating filter
        const ratingCheckboxes = document.querySelectorAll('.filter-checkbox[value*="★"]');
        const selectedRating = Array.from(ratingCheckboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => parseInt(checkbox.value))
            .sort((a, b) => b - a)[0] || 0;
        
        // Get availability filter
        const availabilityCheckboxes = document.querySelectorAll('.filter-checkbox[value*="Stock"]');
        const selectedAvailability = Array.from(availabilityCheckboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value)[0] || 'all';
        
        // Update filters
        this.filters.category = selectedCategories.length > 0 ? selectedCategories[0] : this.filters.category;
        this.filters.brand = selectedBrands;
        this.filters.priceRange = { min: minPrice, max: maxPrice };
        this.filters.rating = selectedRating;
        this.filters.availability = selectedAvailability;
        
        this.renderProducts();
    }

    clearFilters() {
        this.filters = {
            category: '',
            priceRange: { min: 0, max: 100000 },
            rating: 0,
            brand: [],
            availability: 'all'
        };
        
        // Clear all checkboxes
        document.querySelectorAll('.filter-checkbox').forEach(checkbox => {
            checkbox.checked = false;
        });
        
        // Clear price inputs
        document.querySelectorAll('.price-input').forEach(input => {
            input.value = '';
        });
        
        // Reset sort
        const sortSelect = document.querySelector('.sort-select');
        if (sortSelect) {
            sortSelect.value = 'relevance';
        }
        
        // Update URL to remove category parameter
        const url = new URL(window.location);
        url.searchParams.delete('category');
        window.history.replaceState({}, '', url);
        
        // Update page title and breadcrumb
        this.updatePageForCategory('');
        this.updateActiveCategory('');
        
        this.loadProducts(0, 20);
        this.showToast('Filters cleared', 'success');
    }

    openProductDetails(productId) {
        window.location.href = `products.html?id=${productId}`;
    }

    async handleLogin(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        const email = formData.get('email');
        const password = formData.get('password');

        try {
            const result = await this.login(email, password);
            this.showToast('Login successful!', 'success');
            
            // Redirect based on user role
            setTimeout(() => {
                if (result.user.role === 'ADMIN') {
                    window.location.href = 'admin.html';
                } else {
                    window.location.href = 'products.html';
                }
            }, 1000);
        } catch (error) {
            this.showToast(error.message, 'error');
        }
    }

    async handleRegister(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        const userData = {
            name: formData.get('name'),
            email: formData.get('email'),
            password: formData.get('password'),
            phone: formData.get('phone'),
            role: 'CUSTOMER'
        };

        try {
            await this.register(userData);
            this.showToast('Registration successful! Please login.', 'success');
            setTimeout(() => {
                // Stay on the same page but switch to login form
                if (window.location.pathname.includes('user-login.html')) {
                    // Trigger the toggle to show login form
                    if (typeof toggleForm === 'function') {
                        toggleForm('login');
                    }
                } else {
                    window.location.href = 'user-login.html';
                }
            }, 1000);
        } catch (error) {
            this.showToast(error.message, 'error');
        }
    }

    async handleCheckout(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        
        const orderData = {
            shippingAddress: formData.get('address'),
            shippingCity: formData.get('city'),
            shippingState: formData.get('state'),
            shippingPostalCode: formData.get('postalCode'),
            paymentMethod: formData.get('paymentMethod'),
            notes: formData.get('notes')
        };

        try {
            await this.createOrder(orderData);
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 1500);
        } catch (error) {
            this.showToast(error.message, 'error');
        }
    }

    async handleProductUpload(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        
        try {
            // For now, simulate API call
            await new Promise(resolve => setTimeout(resolve, 2000));
            
            this.showToast('Product uploaded successfully!', 'success');
            e.target.reset();
            
            // Clear file input
            const fileInput = e.target.querySelector('input[type="file"]');
            if (fileInput) {
                fileInput.value = '';
            }
        } catch (error) {
            this.showToast('Upload failed. Please try again.', 'error');
        }
    }

    showToast(message, type = 'success') {
        const toast = document.createElement('div');
        toast.className = `toast toast-${type}`;
        toast.textContent = message;
        
        document.body.appendChild(toast);
        
        setTimeout(() => {
            toast.classList.add('show');
        }, 100);
        
        setTimeout(() => {
            toast.classList.remove('show');
            setTimeout(() => {
                document.body.removeChild(toast);
            }, 300);
        }, 3000);
    }

    showLoading() {
        const loading = document.createElement('div');
        loading.className = 'loading-overlay';
        loading.innerHTML = `
            <div class="loading-spinner">
                <div class="spinner"></div>
                <p>Loading products...</p>
            </div>
        `;
        loading.style.cssText = `
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(255, 255, 255, 0.9);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 10000;
        `;
        
        document.body.appendChild(loading);
    }

    hideLoading() {
        const loading = document.querySelector('.loading-overlay');
        if (loading) {
            document.body.removeChild(loading);
        }
    }

    // Initialize cart on page load if user is logged in
    async initializeCart() {
        if (this.token) {
            await this.getCart();
        }
    }
}

// Initialize the app when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    window.app = new VineethaEcommerce();
    window.app.initializeCart();
});

// Export for global access
window.VineethaEcommerce = VineethaEcommerce; 