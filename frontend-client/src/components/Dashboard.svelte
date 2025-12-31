<script>
    import { onMount } from 'svelte';
    import { callApi, logout } from '../lib/keycloak-auth.js';
    
    let accessToken = '';
    let userData = null;
    let loading = true;
    let error = '';
    
    onMount(() => {
      // Check if user is logged in
      accessToken = localStorage.getItem('access_token') || '';
      
      if (!accessToken) {
        // No token, redirect to login
        window.location.href = '/';
        return;
      }
      
      // Fetch user profile
      fetchUserProfile();
    });
    
    async function fetchUserProfile() {
      try {
        const data = await callApi('/api/user/profile', accessToken);
        userData = data;
        loading = false;
      } catch (err) {
        console.error('Failed to fetch user data:', err);
        error = 'Failed to load profile. Please try logging in again.';
        loading = false;
        
        // If unauthorized, redirect to login
        if (err.status === 401) {
          setTimeout(() => {
            logout();
          }, 2000);
        }
      }
    }
    
    function handleLogout() {
      logout();
    }
  </script>
  
  <div class="dashboard">
    <header>
      <h1>E-Prescription Dashboard</h1>
      <button on:click={handleLogout} class="logout-btn">
        Logout
      </button>
    </header>
    
    {#if loading}
      <div class="loading">
        <div class="spinner"></div>
        <p>Loading profile...</p>
      </div>
    {:else if error}
      <div class="error">
        <p>{error}</p>
      </div>
    {:else if userData}
      <div class="profile-card">
        <h2>Welcome, {userData.username}!</h2>
        <div class="profile-info">
          <div class="info-row">
            <strong>Email:</strong>
            <span>{userData.email}</span>
          </div>
          <div class="info-row">
            <strong>Roles:</strong>
            <span>{userData.roles ? userData.roles.join(', ') : 'None'}</span>
          </div>
        </div>
      </div>
    {/if}
  </div>
  
  <style>
    .dashboard {
      min-height: 100vh;
      background: #f5f5f5;
    }
  
    header {
      background: white;
      padding: 1.5rem 2rem;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
  
    h1 {
      margin: 0;
      color: #333;
      font-size: 1.5rem;
    }
  
    .logout-btn {
      background: #dc3545;
      color: white;
      border: none;
      padding: 0.5rem 1.5rem;
      border-radius: 6px;
      cursor: pointer;
      font-size: 0.9rem;
      transition: all 0.3s ease;
    }
  
    .logout-btn:hover {
      background: #c82333;
      transform: translateY(-1px);
    }
  
    .loading, .error {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      padding: 4rem 2rem;
    }
  
    .spinner {
      width: 40px;
      height: 40px;
      border: 4px solid #e0e0e0;
      border-top-color: #667eea;
      border-radius: 50%;
      animation: spin 1s linear infinite;
      margin-bottom: 1rem;
    }
  
    @keyframes spin {
      to { transform: rotate(360deg); }
    }
  
    .error p {
      color: #dc3545;
      font-size: 1.1rem;
    }
  
    .profile-card {
      max-width: 600px;
      margin: 2rem auto;
      background: white;
      padding: 2rem;
      border-radius: 12px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }
  
    h2 {
      color: #333;
      margin-bottom: 1.5rem;
      font-size: 1.8rem;
    }
  
    .profile-info {
      display: flex;
      flex-direction: column;
      gap: 1rem;
    }
  
    .info-row {
      display: flex;
      gap: 1rem;
      padding: 0.75rem;
      background: #f8f9fa;
      border-radius: 6px;
    }
  
    .info-row strong {
      min-width: 80px;
      color: #555;
    }
  
    .info-row span {
      color: #333;
    }
  </style>