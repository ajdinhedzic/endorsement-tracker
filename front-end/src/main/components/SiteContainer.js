import React from 'react';
import Navbar from "./global/Navbar";
import PageContent from "./Page";

class SiteContainer extends React.Component {
    render() {
        return (
            <div>
                <Navbar/>
                <PageContent/>
            </div>
        )
    }
}

export default SiteContainer;