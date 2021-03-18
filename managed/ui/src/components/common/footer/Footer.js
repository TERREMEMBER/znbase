// Copyright (c) ZNbase, Inc.

import React, { PureComponent } from 'react';
import './stylesheets/Footer.scss';
import slackLogo from './images/slack-logo-full.svg';
import githubLogo from './images/github-light-small.png';
import ybLogoImage from '../YBLogo/images/yb_ybsymbol_dark.png';
import YBLogo from '../YBLogo/YBLogo';
import * as moment from 'moment';
import { getPromiseState } from '../../../utils/PromiseUtils';

class Footer extends PureComponent {
  render() {
    const {
      customer: { yugawareVersion }
    } = this.props;
    const version = getPromiseState(yugawareVersion).isSuccess()
      ? yugawareVersion.data.version
      : null;
    return (
      <footer>
        <div className="footer-logo-container">
          <YBLogo type="monochrome" />
          {version && <span> Version: {version.substr(0, version.indexOf('-'))}</span>}
        </div>
        <div className="footer-social-container">
          <a href="https://www.ZNbase.com/slack" target="_blank" rel="noopener noreferrer">
            <span className="social-media-cta">
              Join us on
              <img alt="ZNbaseDB Slack" src={slackLogo} width="65" />
            </span>
          </a>
          <a
            href="https://github.com/ZNbase/ZNbase-db/"
            target="_blank"
            rel="noopener noreferrer"
          >
            <span className="social-media-cta">
              Star us on
              <img
                alt="ZNbaseDB GitHub"
                className="social-media-logo"
                src={githubLogo}
                width="18"
              />{' '}
              <b>GitHub</b>
            </span>
          </a>
          <a
            href="https://www.ZNbase.com/community-rewards/"
            target="_blank"
            rel="noopener noreferrer"
          >
            <span className="social-media-cta">
              Free t-shirt at
              <img
                alt="ZNbaseDB Community Rewards"
                className="social-media-logo"
                src={ybLogoImage}
                width="100"
              />
            </span>
          </a>
        </div>
        <div className="copyright">&copy; {moment().get('year')} ZNbase, Inc.</div>
      </footer>
    );
  }
}

export default Footer;
