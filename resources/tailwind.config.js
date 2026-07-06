const defaultTheme = require('tailwindcss/defaultTheme')

module.exports = {
  content: [
    './src/**/*',
    './resources/**/*',
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Poppins', ...defaultTheme.fontFamily.sans],
      },
      colors: {
        brand: {
          50: '#f7fbec',
          100: '#eef6d9',
          200: '#dceeb0',
          300: '#bfe066',
          400: '#a0d323',
          500: '#96bf3a',
          600: '#82a62b',
          700: '#7aa218',
          800: '#4b6b03',
          900: '#344c01',
        },
      },
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
