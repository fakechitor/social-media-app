package com.fakechitor.socialmediaauthorization.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails : UserDetails {
    private var userId: Long? = null
    private var username: String? = null
    private var password: String? = null
    private var authorities: Collection<GrantedAuthority?>? = null

    fun getUserId(): Long? = userId

    constructor(userId: Long?, username: String?, password: String?, authorities: Collection<GrantedAuthority?>?) {
        this.userId = userId
        this.username = username
        this.password = password
        this.authorities = authorities
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? = authorities

    override fun getPassword(): String? = password

    override fun getUsername(): String? = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
