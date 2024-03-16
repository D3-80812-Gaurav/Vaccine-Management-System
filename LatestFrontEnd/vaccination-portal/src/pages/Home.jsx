import React from 'react'
import Navbar from '../components/Navbar'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
import image1 from '../images/CaraouselImage1.jpg'
import image2 from '../images/CaraouselImage2.jpg'
import image3 from '../images/CaraouselImage3.jpg'
import Footer from '../components/Footer'

export default function Home() {
    return (
        <>
            <Navbar></Navbar>
            <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src={image1} class="d-block w-100" alt="..." />
                    </div>
                    <div class="carousel-item">
                        <img src={image2} class="d-block w-100" alt="..." />
                    </div>
                    <div class="carousel-item">
                        <img src={image3} class="d-block w-100" alt="..." />
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <Footer></Footer>
        </>
    )
}
