/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: "/api/v1/:path*",
        destination: `${process.env.API_BACKEND_URL ?? "http://backend:8080"}/api/v1/:path*`,
      },
    ];
  },
};

export default nextConfig;
