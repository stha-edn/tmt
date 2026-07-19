const defaultTheme = require('tailwindcss/defaultTheme')

module.exports = {
  content: [
    './src/**/*.clj',
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Poppins', ...defaultTheme.fontFamily.sans],
      },
      colors: {
        brand: {
          50: '#f0faf5',
          100: '#d9f2e8',
          200: '#b3e5d1',
          300: '#75d4ae',
          400: '#41be8a',
          500: '#29a676',
          600: '#1a865e',
          700: '#166b4c',
          800: '#15553e',
          900: '#134633',
        },
      },
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
