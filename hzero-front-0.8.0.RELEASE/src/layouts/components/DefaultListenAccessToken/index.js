/**
 * 监听 access_token 变化 来判断是否需要 刷新页面
 */

import React from 'react';

import { getAccessToken } from 'utils/utils';

class DefaultListenAccessToken extends React.Component {
  componentDidMount() {
    // eslint-disable-next-line
    window.accessToken = window.accessToken || getAccessToken('access_token');
    this.refreshTimer = setInterval(() => {
      const accessToken = getAccessToken('access_token');
      const windowToken = window.accessToken;
      if (accessToken && windowToken && windowToken !== accessToken) {
        window.location.reload();
      }
    }, 3000);
  }

  componentWillUnmount() {
    clearInterval(this.refreshTimer);
  }

  render() {
    return null;
  }
}

export default DefaultListenAccessToken;
