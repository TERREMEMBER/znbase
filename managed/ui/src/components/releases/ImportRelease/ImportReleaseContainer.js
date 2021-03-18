// Copyright (c) ZNbase, Inc.

import { connect } from 'react-redux';
import { ImportRelease } from '../../../components/releases';
import { importZNbaseRelease, importZNbaseReleaseResponse } from '../../../actions/customers';

const mapDispatchToProps = (dispatch) => {
  return {
    importZNbaseRelease: (payload) => {
      dispatch(importZNbaseRelease(payload)).then((response) => {
        dispatch(importZNbaseReleaseResponse(response.payload));
      });
    }
  };
};

function mapStateToProps(state, ownProps) {
  return {
    initialValues: { version: '' },
    importRelease: state.customer.importRelease
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(ImportRelease);
