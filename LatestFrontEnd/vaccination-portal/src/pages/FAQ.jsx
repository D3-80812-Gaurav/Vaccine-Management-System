import React from 'react'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer'

export default function FAQ() {
    return (
        <>
            <Navbar />
            <div className="container mt-5 mb-auto">
                <div className="accordion" id="accordionExample">
                    <div className="accordion-item">
                        <h2 className="accordion-header">
                            <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                1. Why should I get vaccinated?
                            </button>
                        </h2>
                        <div id="collapseOne" className="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                Vaccines provide protection against severe illness, hospitalization, and death from COVID-19. They also help to reduce the spread of the virus to others, especially those who are at high risk of complications.
                            </div>
                        </div>
                    </div>
                    <div className="accordion-item">
                        <h2 className="accordion-header">
                            <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                2. What are the different types of COVID-19 vaccines?
                            </button>
                        </h2>
                        <div id="collapseTwo" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                There are two main types of COVID-19 vaccines: mRNA vaccines and viral vector vaccines. mRNA vaccines teach your cells how to make a protein that triggers an immune response to the virus. Viral vector vaccines use a harmless virus to deliver genetic material from the SARS-CoV-2 virus to your cells, which then triggers an immune response.
                            </div>
                        </div>
                    </div>
                    <div className="accordion-item">
                        <h2 className="accordion-header">
                            <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                3. How many doses of the vaccine do I need?
                            </button>
                        </h2>
                        <div id="collapseThree" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                The number of doses you need depends on the specific vaccine you receive. Most adults need two doses, while some children need three doses.
                            </div>
                        </div>
                    </div>
                    <div className="accordion-item">
                        <h2 className="accordion-header">
                            <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                4. What are the side effects of the vaccine?
                            </button>
                        </h2>
                        <div id="collapseFour" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                The most common side effects of the COVID-19 vaccines are mild and short-lived, such as pain, redness, or swelling at the injection site, tiredness, headache, muscle pain, chills, fever, and nausea. Serious side effects are extremely rare.
                            </div>
                        </div>
                    </div>
                    <div className="accordion-item">
                        <h2 className="accordion-header">
                            <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                5. I'm pregnant or breastfeeding. Can I get the vaccine?
                            </button>
                        </h2>
                        <div id="collapseFive" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                The COVID-19 vaccines are safe and effective for pregnant and breastfeeding women. In fact, the CDC recommends that pregnant women get vaccinated to protect themselves and their babies from COVID-19.
                            </div>
                        </div>
                    </div>
                    <div className="accordion-item">
                        <h2 className="accordion-header">
                            <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                6. I already had COVID-19. Do I still need to get vaccinated?
                            </button>
                        </h2>
                        <div id="collapseSix" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                Yes, even if you have already had COVID-19, you should still get vaccinated. Getting vaccinated provides additional protection against getting sick again, especially from new variants of the virus.
                            </div>
                        </div>
                    </div>
                    <div className="accordion-item">
                        <h2 className="accordion-header">
                            <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                                7. Where can I get vaccinated?
                            </button>
                        </h2>
                        <div id="collapseSeven" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                The COVID-19 vaccines are available at many pharmacies, doctor's offices, and other healthcare providers. You can find a list of vaccination sites in your area on the CDC's website.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer></Footer>
        </>
    )
}
