import React from 'react'
import { Switch, Route } from 'react-router-dom';
import BodyCarousel from './BodyCarousel';
import FrequentlyAskedQuestions from './FrequentlyAskedQuestions';
import AdminServices from './AdminServices';
import CitizenServices from './CitizenServices';
import CitizenRegisteration from './CitizenRegisteration';
import CitizenLogin from './Login';
import CitizenDashboard from './CitizenDashboard';
import DownloadCertificate from './DownloadCertificate';

export default function Launcher() {
    return (
        <div className='my-5 pt-1'>
            <Switch>
                <Route path="/" exact component={BodyCarousel} />
                <Route path="/CitizenServices" exact component={CitizenServices} />
                <Route path="/CitizenServices/Register" exact component={CitizenRegisteration} />
                <Route path="/CitizenServices/Login" exact component={CitizenLogin} />
                <Route path="/CitizenServices/Dashboard" exact component={CitizenDashboard} />
                <Route path="/home" exact component={BodyCarousel} />
                <Route path="/FAQ" exact component={FrequentlyAskedQuestions} />
                <Route path="/AdminServices" exact component={AdminServices} />
                <Route path="/CitizenServices/Dashboard/DownloadCertificate" exact component={DownloadCertificate} />
            </Switch>
        </div>
    )
}
