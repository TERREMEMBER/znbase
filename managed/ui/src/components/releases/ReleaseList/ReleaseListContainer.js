// Copyright ZNbase Inc.

import { connect } from 'react-redux';
import { ReleaseList } from '../../../components/releases';
import {
  refreshZNbaseReleases,
  refreshZNbaseReleasesResponse,
  getZNbaseReleases,
  getZNbaseReleasesResponse
} from '../../../actions/customers';

const mapDispatchToProps = (dispatch) => {
  return {
    refreshZNbaseReleases: () => {
      dispatch(refreshZNbaseReleases()).then((response) => {
        dispatch(refreshZNbaseReleasesResponse(response.payload));
      });
    },
    getZNbaseReleases: () => {
      dispatch(getZNbaseReleases()).then((response) => {
        dispatch(getZNbaseReleasesResponse(response.payload));
      });
    }
  };
};

function mapStateToProps(state) {
  return {
    refreshReleases: state.customer.refreshReleases,
    releases: state.customer.releases,
    customer: state.customer
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(ReleaseList);
