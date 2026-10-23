package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ParsTheme
import com.example.vpn.VpnStatus

@Composable
fun HeaderBar(
    vpnStatus: VpnStatus,
    onAddClick: () -> Unit,
    onClipboardClick: () -> Unit,
    onImportFileClick: () -> Unit = {},
    onThemeClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Subtle animated neon energy line beneath the header
    val infiniteTransition = rememberInfiniteTransition(label = "energy_pulse")
    val pulseOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse_offset"
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Action Buttons: Tactical Add (+), File, Clipboard, and "تم دلخواه"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Add Subscription Button (+)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(ParsTheme.palette.cardBg)
                        .border(1.dp, ParsTheme.palette.border, RoundedCornerShape(10.dp))
                        .clickable(onClick = onAddClick)
                        .testTag("header_add_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "افزودن اشتراک",
                        tint = ParsTheme.palette.glowColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Import WireGuard File Button (.conf)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(ParsTheme.palette.cardBg)
                        .border(1.dp, Color(0xFF0284C7).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
                        .clickable(onClick = onImportFileClick)
                        .testTag("header_import_file_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.FileOpen,
                        contentDescription = "افزودن فایل وایرگارد",
                        tint = Color(0xFF38BDF8),
                        modifier = Modifier.size(19.dp)
                    )
                }

                // Clipboard Paste Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(ParsTheme.palette.cardBg)
                        .border(1.dp, ParsTheme.palette.border, RoundedCornerShape(10.dp))
                        .clickable(onClick = onClipboardClick)
                        .testTag("header_clipboard_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Assignment,
                        contentDescription = "چسباندن از کلیپبورد",
                        tint = ParsTheme.palette.textSecondary,
                        modifier = Modifier.size(19.dp)
                    )
                }

                // "تم دلخواه" Settings Button
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(ParsTheme.palette.cardBg)
                        .border(1.2.dp, ParsTheme.palette.glowColor.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .clickable(onClick = onThemeClick)
                        .padding(horizontal = 10.dp)
                        .testTag("header_theme_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "تم دلخواه",
                            tint = ParsTheme.palette.glowColor,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "تم دلخواه",
                            color = ParsTheme.palette.textPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Right: Brand & Tactical Protection Status
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    // Custom Tactical Typographic Brand Treatment
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(ParsTheme.palette.primaryAccent.copy(alpha = 0.2f))
                                .border(0.8.dp, ParsTheme.palette.glowColor.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "PRO",
                                color = ParsTheme.palette.glowColor,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "PARS PING",
                            color = ParsTheme.palette.textPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 1.2.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    // Status Indicator Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val statusColor = when (vpnStatus) {
                            VpnStatus.CONNECTED -> ParsTheme.palette.connectedGreen
                            VpnStatus.CONNECTING -> ParsTheme.palette.glowColor
                            VpnStatus.FAILED -> ParsTheme.palette.errorRed
                            else -> ParsTheme.palette.textMuted
                        }
                        val statusLabel = when (vpnStatus) {
                            VpnStatus.CONNECTED -> "محافظت فعال // SECURE"
                            VpnStatus.CONNECTING -> "در حال اتصال..."
                            VpnStatus.FAILED -> "خطای اتصال // ALERT"
                            else -> "غیرفعال // STANDBY"
                        }

                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(statusColor)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = statusLabel,
                            color = statusColor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.3.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Preserved Tactical Cyber Crown Emblem
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    ParsTheme.palette.cardBg,
                                    ParsTheme.palette.dimColor
                                )
                            )
                        )
                        .border(1.2.dp, ParsTheme.palette.glowColor.copy(alpha = 0.6f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    ParsPingCrown(
                        modifier = Modifier.size(24.dp),
                        primaryColor = ParsTheme.palette.primaryAccent,
                        glowColor = ParsTheme.palette.glowColor,
                        secondaryColor = ParsTheme.palette.secondaryDark
                    )
                }
            }
        }

        // Animated neon energy pulse line beneath header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            ParsTheme.palette.primaryAccent.copy(alpha = 0.4f),
                            ParsTheme.palette.glowColor,
                            ParsTheme.palette.primaryAccent.copy(alpha = 0.4f),
                            Color.Transparent
                        ),
                        startX = pulseOffset * 400f,
                        endX = (pulseOffset + 1f) * 400f
                    )
                )
        )
    }
}
