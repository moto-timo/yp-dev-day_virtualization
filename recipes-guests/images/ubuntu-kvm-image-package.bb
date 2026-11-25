SUMMARY = "Ubuntu cloud kvm image"
# The image is an aggregate of packages with their own licenses; see the
# license text and the Ubuntu manifest installed next to the image.
LICENSE = "LicenseRef-Ubuntu-Cloud-Image"
LIC_FILES_CHKSUM = "file://${YPDD_LICENSE_DIR}/LicenseRef-Ubuntu-Cloud-Image;md5=509f51d5e678db9af2a13c2538628826"

inherit kvm-binary-guest-package

# Ubuntu stopped publishing the -disk-kvm.img variant after jammy (22.04);
# the generic cloud image is a qcow2 disk that boots under KVM.
UBUNTU_BASE_URL ??= "https://cloud-images.ubuntu.com"
UBUNTU_RELEASE ??= "resolute"
UBUNTU_IMAGE_ARCH ??= "amd64"
UBUNTU_IMAGE_BASENAME ?= "${UBUNTU_RELEASE}-server-cloudimg-${UBUNTU_IMAGE_ARCH}"
UBUNTU_IMAGE_NAME ?= "${UBUNTU_IMAGE_BASENAME}.img"
UBUNTU_MANIFEST_NAME ?= "${UBUNTU_IMAGE_BASENAME}.manifest"
UBUNTU_IMAGE_DATE ?= "current"
VESSEL_PAYLOAD_NAME = "${UBUNTU_IMAGE_NAME}"

UBUNTU_IMAGE_URI = "${UBUNTU_BASE_URL}/${UBUNTU_RELEASE}/${UBUNTU_IMAGE_DATE}"
SRC_URI = " \
    ${UBUNTU_IMAGE_URI}/${UBUNTU_IMAGE_NAME};name=image \
    ${UBUNTU_IMAGE_URI}/${UBUNTU_MANIFEST_NAME};name=manifest \
"
SHA256SUMS_URI = "${UBUNTU_IMAGE_URI}/SHA256SUMS"

# See  http://www.burtonini.com/blog/2017/06/13/dynamic-source-checksums
do_fetch[prefuncs] += "fetch_checksums"

python fetch_checksums() {
    import urllib.request

    wanted = {
        "*" + d.getVar("UBUNTU_IMAGE_NAME"): "image",
        "*" + d.getVar("UBUNTU_MANIFEST_NAME"): "manifest",
    }
    for line in urllib.request.urlopen(d.getVar("SHA256SUMS_URI")):
        (sha256, filename) = line.decode("ascii").strip().split()
        name = wanted.pop(filename, None)
        if name:
            d.setVarFlag("SRC_URI", name + ".sha256sum", sha256)
    for filename in wanted:
        bb.error("Could not find remote checksum for %s" % filename)
}

do_install:append() {
	install -m 0644 ${UNPACKDIR}/${UBUNTU_MANIFEST_NAME} ${D}${vesseldir}/
}
