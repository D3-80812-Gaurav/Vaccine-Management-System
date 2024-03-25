import Navbar from '../components/Navbar'
import CenterHeader from '../components/CenterHeader';
import Appointments from '../components/Appointments';

export default function CenterDashboard() {
    return (
        <>
            <Navbar />
            <CenterHeader />
            <div>
                <Appointments />
            </div>
        </>
    )
}
