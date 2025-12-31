// keycloak-auth.js - Simple Keycloak OAuth2 helper

// Get config from environment variables (set via import.meta.env in Astro)
const KEYCLOAK_URL = import.meta.env.PUBLIC_KEYCLOAK_URL || 'http://localhost:8081';
const REALM = import.meta.env.PUBLIC_KEYCLOAK_REALM || 'eprescription-realm';
const CLIENT_ID = import.meta.env.PUBLIC_KEYCLOAK_CLIENT_ID || 'eprescription-client';
const CLIENT_SECRET = import.meta.env.PUBLIC_KEYCLOAK_CLIENT_SECRET || 'eprescription-secret';
const REDIRECT_URI = import.meta.env.PUBLIC_REDIRECT_URI || 'http://localhost:4321/callback';
const BACKEND_URL = import.meta.env.PUBLIC_BACKEND_URL || 'http://localhost:8080';

// 1. Redirect to Keycloak login
export function login() {
  const authUrl = `${KEYCLOAK_URL}/realms/${REALM}/protocol/openid-connect/auth`;
  const params = new URLSearchParams({
    client_id: CLIENT_ID,
    redirect_uri: REDIRECT_URI,
    response_type: 'code',
    scope: 'openid profile email',
  });
  
  window.location.href = `${authUrl}?${params}`;
}

// 2. Exchange code for token (call this in callback page)
export async function getToken(code) {
  const tokenUrl = `${KEYCLOAK_URL}/realms/${REALM}/protocol/openid-connect/token`;
  
  const response = await fetch(tokenUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: new URLSearchParams({
      grant_type: 'authorization_code',
      client_id: CLIENT_ID,
      client_secret: CLIENT_SECRET,
      code: code,
      redirect_uri: REDIRECT_URI,
    }),
  });
  
  return response.json();
}

// 3. Call your Spring Boot API
export async function callApi(endpoint, accessToken, options = {}) {
  const response = await fetch(`${BACKEND_URL}${endpoint}`, {
    ...options,
    headers: {
      'Authorization': `Bearer ${accessToken}`,
      'Content-Type': 'application/json',
      ...options.headers,
    },
  });
  
  return response.json();
}

// 4. Logout
export function logout() {
  const logoutUrl = `${KEYCLOAK_URL}/realms/${REALM}/protocol/openid-connect/logout`;
  const params = new URLSearchParams({
    client_id: CLIENT_ID,
    post_logout_redirect_uri: 'http://localhost:4321',
  });
  
  // Clear tokens from storage
  localStorage.removeItem('access_token');
  localStorage.removeItem('refresh_token');
  
  window.location.href = `${logoutUrl}?${params}`;
}

export async function refreshAccessToken() {
  const refreshToken = localStorage.getItem('refresh_token');
  const clientId = 'your-client-id'; // Make sure this matches Keycloak
  const realm = 'eprescription-realm';
  const keycloakUrl = 'http://localhost:8081';

  if (!refreshToken) throw new Error('No refresh token available');

  const params = new URLSearchParams();
  params.append('grant_type', 'refresh_token');
  params.append('client_id', clientId);
  params.append('refresh_token', refreshToken);

  const response = await fetch(`${keycloakUrl}/realms/${realm}/protocol/openid-connect/token`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: params
  });

  if (response.ok) {
      const data = await response.json();
      localStorage.setItem('access_token', data.access_token);
      localStorage.setItem('refresh_token', data.refresh_token);
      return data.access_token;
  } else {
      localStorage.clear();
      window.location.href = '/'; // Force login if refresh fails
      throw new Error('Session expired');
  }
}