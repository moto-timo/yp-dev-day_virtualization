SUMMARY = "Ubuntu cloud kvm image"
# Probably this should be Canonical IPRights?
LICENSE="CLOSED"

inherit kvm-binary-guest-package

# precise, xenial and bionic do not have kvm images
# eoan, focal and groovy do
UBUNTU_BASE_URL ??= "https://cloud-images.ubuntu.com"
UBUNTU_RELEASE ??="focal"
UBUNTU_IMAGE_ARCH ??="amd64"
UBUNTU_IMAGE_NAME ?= "${UBUNTU_RELEASE}-server-cloudimg-${UBUNTU_IMAGE_ARCH}-disk-kvm.img"
UBUNTU_IMAGE_DATE ?= "current"
VESSEL_PAYLOAD_NAME = "${UBUNTU_IMAGE_NAME}"

SRC_URI = "${UBUNTU_BASE_URL}/${UBUNTU_RELEASE}/${UBUNTU_IMAGE_DATE}/${UBUNTU_IMAGE_NAME}"
SHA256SUMS_URI = "${UBUNTU_BASE_URL}/${UBUNTU_RELEASE}/${UBUNTU_IMAGE_DATE}/SHA256SUMS"

# Sse  http://www.burtonini.com/blog/2017/06/13/dynamic-source-checksums
do_fetch[prefuncs]  += "fetch_checksums"

python fetch_checksums() {
    import re
    import urllib
    
    match = "*{}".format(d.getVar("UBUNTU_IMAGE_NAME"))
    for line in urllib.request.urlopen(d.getVar("SHA256SUMS_URI")):
        (sha256, filename) = line.decode("ascii").strip().split()
        if filename == match:
            d.setVarFlag("SRC_URI", "sha256sum", sha256)
            return
    bb.error("Could not find remote checksum for %s" %  filename)
}
