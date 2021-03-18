// Copyright (c) ZNbase, Inc.

import { connect } from 'react-redux';
import { UpdateRelease } from '../../../components/releases';
import { updateZNbaseRelease, updateZNbaseReleaseResponse } from '../../../actions/customers';

const mapDispatchToProps = (dispatch) => {
  return {
    updateZNbaseRelease: (version, payload) => {
      dispatch(updateZNbaseRelease(version, payload)).then((response) => {
        dispatch(updateZNbaseReleaseResponse(response.payload));
      });
    }
  };
};

function mapStateToProps(state, ownProps) {
  return {
    updateRelease: state.customer.updateRelease
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(UpdateRelease);
