#!/bin/bash

# Vineetha E-commerce Quick Start Script
# This script helps you get the project up and running quickly

echo "🚀 Vineetha E-commerce Quick Start"
echo "=================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if MySQL is running
check_mysql() {
    print_status "Checking MySQL service..."
    if mysqladmin ping -h localhost -u root -pvinish@2008 --silent; then
        print_success "MySQL is running"
        return 0
    else
        print_error "MySQL is not running. Please start MySQL first."
        echo "On macOS: brew services start mysql"
        echo "On Linux: sudo systemctl start mysql"
        echo "On Windows: Start MySQL service from Services"
        return 1
    fi
}

# Setup database
setup_database() {
    print_status "Setting up database..."
    
    # Check if database exists
    if mysql -u root -pvinish@2008 -e "USE vineetha_db;" 2>/dev/null; then
        print_warning "Database 'vineetha_db' already exists"
        read -p "Do you want to recreate it? (y/N): " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            mysql -u root -pvinish@2008 -e "DROP DATABASE IF EXISTS vineetha_db;"
            print_status "Database dropped"
        else
            print_status "Using existing database"
            return 0
        fi
    fi
    
    # Create database and load data
    if mysql -u root -pvinish@2008 < backend/database/init.sql; then
        print_success "Database setup completed"
        
        # Show database stats
        echo
        print_status "Database Statistics:"
        mysql -u root -pvinish@2008 -e "
        USE vineetha_db;
        SELECT 'Users' as table_name, COUNT(*) as count FROM users
        UNION ALL
        SELECT 'Products', COUNT(*) FROM products
        UNION ALL
        SELECT 'Orders', COUNT(*) FROM orders
        UNION ALL
        SELECT 'Cart Items', COUNT(*) FROM cart_items
        UNION ALL
        SELECT 'Reviews', COUNT(*) FROM reviews;
        " 2>/dev/null || print_warning "Could not display database stats"
    else
        print_error "Database setup failed"
        return 1
    fi
}

# Check Java installation
check_java() {
    print_status "Checking Java installation..."
    if java -version 2>&1 | grep -q "version"; then
        print_success "Java is installed"
        java -version 2>&1 | head -n 1
    else
        print_error "Java is not installed. Please install Java 21 or higher."
        echo "Download from: https://adoptium.net/"
        return 1
    fi
}

# Check Maven installation
check_maven() {
    print_status "Checking Maven installation..."
    if mvn -version 2>&1 | grep -q "Apache Maven"; then
        print_success "Maven is installed"
        mvn -version 2>&1 | head -n 1
    else
        print_error "Maven is not installed. Please install Maven."
        echo "On macOS: brew install maven"
        echo "On Linux: sudo apt install maven"
        echo "On Windows: Download from https://maven.apache.org/"
        return 1
    fi
}

# Build and start backend
start_backend() {
    print_status "Building backend project..."
    cd backend
    
    if mvn clean compile; then
        print_success "Backend compiled successfully"
        
        print_status "Starting backend server..."
        echo "Backend will be available at: http://localhost:8080"
        echo "API base URL: http://localhost:8080/api"
        echo "Press Ctrl+C to stop the server"
        echo
        
        # Start the application
        mvn spring-boot:run
        
    else
        print_error "Backend compilation failed"
        cd ..
        return 1
    fi
}

# Show next steps
show_next_steps() {
    echo
    echo "🎉 Setup Complete!"
    echo "=================="
    echo
    echo "Next Steps:"
    echo "1. Backend is running at http://localhost:8080"
    echo "2. Test the API: curl http://localhost:8080/api/health"
    echo "3. Open frontend: Open index.html in your browser"
    echo "4. Test user registration and login"
    echo
    echo "Default Credentials:"
    echo "- Admin: admin@vineetha.in / password"
    echo "- Customer: aarav.sharma1@example.com / password1"
    echo
    echo "Documentation:"
    echo "- API Testing Guide: API_TESTING_GUIDE.md"
    echo "- Database Setup: backend/DATABASE_SETUP.md"
    echo "- Project Summary: PROJECT_SUMMARY.md"
    echo
    echo "Happy coding! 🚀"
}

# Main execution
main() {
    echo "This script will help you set up the Vineetha E-commerce project."
    echo "Make sure you have MySQL, Java 21+, and Maven installed."
    echo
    
    # Check prerequisites
    if ! check_mysql; then
        exit 1
    fi
    
    if ! check_java; then
        exit 1
    fi
    
    if ! check_maven; then
        exit 1
    fi
    
    # Setup database
    if ! setup_database; then
        exit 1
    fi
    
    # Show next steps
    show_next_steps
    
    # Ask if user wants to start backend
    read -p "Do you want to start the backend server now? (Y/n): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Nn]$ ]]; then
        print_status "You can start the backend later with: cd backend && mvn spring-boot:run"
        exit 0
    fi
    
    # Start backend
    start_backend
}

# Handle script interruption
trap 'echo -e "\n${YELLOW}Setup interrupted. You can restart anytime.${NC}"; exit 1' INT

# Run main function
main 