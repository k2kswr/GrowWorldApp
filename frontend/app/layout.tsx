import "./styles.css"; import type { Metadata } from "next";
export const metadata: Metadata={title:"Grow World",description:"毎日の行動で、世界を育てよう。"};
export default function Layout({children}:{children:React.ReactNode}){return <html lang="ja"><body>{children}</body></html>}
