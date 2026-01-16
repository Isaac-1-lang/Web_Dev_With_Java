-- SQL Script to add email verification columns to users table
-- Run this script on your PostgreSQL database

-- Add is_verified column (boolean, default false)
ALTER TABLE users 
ADD COLUMN IF NOT EXISTS is_verified BOOLEAN DEFAULT false;

-- Add verification_code column (varchar to store 6-digit code)
ALTER TABLE users 
ADD COLUMN IF NOT EXISTS verification_code VARCHAR(6);

-- Update existing users to be verified (optional - only if you want existing users to be verified)
-- UPDATE users SET is_verified = true WHERE is_verified IS NULL;

-- Create index on email for faster lookups during verification
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);

-- Create index on verification_code for faster verification lookups
CREATE INDEX IF NOT EXISTS idx_users_verification_code ON users(verification_code);

-- Verify the changes
-- SELECT column_name, data_type, column_default 
-- FROM information_schema.columns 
-- WHERE table_name = 'users' 
-- AND column_name IN ('is_verified', 'verification_code');
