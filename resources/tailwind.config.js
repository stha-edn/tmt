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
          50: '#f0f7ff',
          100: '#e0effe',
          200: '#b9dffb',
          300: '#7cc5f8',
          400: '#36a9f2',
          500: '#0c8ee2',
          600: '#0070c0',
          700: '#015a9b',
          800: '#064c80',
          900: '#0b406a',
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
