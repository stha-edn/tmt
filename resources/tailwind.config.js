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
          50: '#faf8f5',
          100: '#f0ece6',
          200: '#e0d8ce',
          300: '#c9bdb0',
          400: '#ad9d8c',
          500: '#907d6a',
          600: '#736150',
          700: '#5c4d3f',
          800: '#453a30',
          900: '#23201e',
        },
      },
      boxShadow: {
        'neu': '6px 6px 12px rgba(0,0,0,0.06), -6px -6px 12px rgba(255,255,255,0.8)',
        'neu-lg': '12px 12px 24px rgba(0,0,0,0.06), -12px -12px 24px rgba(255,255,255,0.8)',
        'neu-sm': '3px 3px 6px rgba(0,0,0,0.04), -3px -3px 6px rgba(255,255,255,0.8)',
        'neu-inset': 'inset 2px 2px 4px rgba(0,0,0,0.04), inset -2px -2px 4px rgba(255,255,255,0.8)',
        'neu-hover': '8px 8px 16px rgba(0,0,0,0.08), -8px -8px 16px rgba(255,255,255,0.9)',
      },
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
