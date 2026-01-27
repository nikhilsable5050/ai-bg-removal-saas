import BgRemovalSteps from "../components/BgRemovalSteps.jsx";
import BgSlider from "../components/BgSilder.jsx";
import Header from "../components/Header.jsx";
import Pricing from "../components/Pricing.jsx";
import Testimonials from "../components/Testimonials.jsx";

const Home = () => {
    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16 font-['Outfit]">
            {/*  Hero section */}
            <Header />
            
            {/*  Background removal steps section */}
            <BgRemovalSteps />
            
            {/*  Background removal slider section */}
            <BgSlider />

            {/*  Buy credits plan section */}
            <Pricing />

            {/*  User testimonials section */}
            <Testimonials />

            {/*  Try now section */}
            
        </div>
    )
}

export default Home;