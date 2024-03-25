import React from 'react'
import Navbar from '../components/Navbar'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
import Footer from '../components/Footer'
import WelcomeAndLinks from '../components/WelcomeAndLinks'
import Features from '../components/Features'
export default function Home() {
    return (
        <>
            <Navbar></Navbar>
            <WelcomeAndLinks />
            <Features />
            <Footer></Footer>
        </>
    )
}
